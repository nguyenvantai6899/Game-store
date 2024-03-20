package com.gamesstorebe.service.impl;

import com.gamesstorebe.dto.UserDTO;
import com.gamesstorebe.entity.User;
import com.gamesstorebe.repository.UserRepository;
import com.gamesstorebe.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service(value = "accountService")
public class AccountServiceImpl implements AccountService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(UserRepository userRepository, ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO findUserByUserEmail(String userEmail) {
        return userRepository.findById(userEmail).map(user -> mapper.map(user, UserDTO.class)).orElse(null);
    }

    @Override
    public UserDTO updateInfoUser(UserDTO user) {
        User account = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (account != null) {
            account.setFirstName(user.getFirstName());
            account.setLastName(user.getLastName());
            account.setDOB(user.getDOB());
            account.setAddress(user.getAddress());
            userRepository.save(account);
        }
        return userRepository.findById(user.getEmail()).map(user1 -> mapper.map(user1, UserDTO.class)).orElse(null);
    }

    @Override
    public String changePassword(String userEmail, String currentPassword, String newPassword) {
        User user = userRepository.findById(userEmail).orElse(null);
        if (user != null) {
            if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
                return "Password do not match";
            }
            user.setPassword(newPassword);
            userRepository.save(user);
        }
        return "Password changed";
    }
}
