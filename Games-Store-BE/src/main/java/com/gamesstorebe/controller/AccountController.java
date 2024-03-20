package com.gamesstorebe.controller;

import com.gamesstorebe.customHandleError.system.Result;
import com.gamesstorebe.dto.UserDTO;
import com.gamesstorebe.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PutMapping("/{userEmail}")
    public Result GetAccountByUserEmail(@PathVariable(value = "userEmail") String userEmail){
        return new Result(true, HttpStatus.OK, "account", accountService.findUserByUserEmail(userEmail));
    }

    @PostMapping("/change-password")
    public Result EditPassword(@RequestParam(value = "email") String email,
                               @RequestParam(value = "currentPassword") String currentPassword,
                               @RequestParam(value = "newPassword") String newPassword){
        return new Result(true, HttpStatus.OK, "account", accountService.changePassword(email, currentPassword, newPassword));
    }

    @PostMapping(value = "/update-account", consumes = "application/json")
    public Result UpdateAccount(@RequestBody UserDTO userDTO){
        return new Result(true, HttpStatus.OK, "account", accountService.updateInfoUser(userDTO));
    }
}
