
package com.gamesstorebe.service.impl;


import com.gamesstorebe.entity.Cart;
import com.gamesstorebe.entity.Role;
import com.gamesstorebe.entity.User;
import com.gamesstorebe.repository.CartRepository;
import com.gamesstorebe.repository.RoleRepository;
import com.gamesstorebe.repository.UserRepository;
import com.gamesstorebe.customHandleError.system.Result;
import com.gamesstorebe.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CartService cartService;

    public Result registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return new Result(false, HttpStatus.CONFLICT, "User already registered", null);
        }
        if (user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            return new Result(false, HttpStatus.BAD_REQUEST, "Username or password is empty", null);
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        Role role = roleRepository.findByRole("USER").orElseThrow(() -> new RuntimeException("Role not found"));
        Set<Role> authorities = new HashSet<>();
        authorities.add(role);
        user.setUserRole(authorities);
        user.setStatus(true);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        cartService.addCartByUser(user.getEmail());
        return new Result(true, HttpStatus.OK, "Register successfully", userRepository.findByEmail(user.getEmail()));
    }

    public Result loginUser(String email, String password){
        Map<String, Object> loginResultMap = new HashMap<>();
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Email not found"));
                String token = tokenService.generateToken(auth);
                loginResultMap.put("userInfo", user);
                loginResultMap.put("token", token);
                return new Result(true, HttpStatus.OK, "Register successfully", loginResultMap);
    }
}

