package com.gamesstorebe.controller;

import com.gamesstorebe.entity.User;
import com.gamesstorebe.service.impl.AuthService;
import com.gamesstorebe.customHandleError.system.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(value = "/register", consumes = "application/json")
    public Result register(@RequestBody User user) {
        return authService.registerUser(user);
    }

    @PostMapping(value = "/login")
    public Result login(@RequestParam ("email") String email , @RequestParam ("password") String password) {
        return authService.loginUser(email, password);
    }

}
