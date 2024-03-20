package com.gamesstorebe.service;

import com.gamesstorebe.dto.UserDTO;
import com.gamesstorebe.entity.User;

public interface AccountService {
    UserDTO findUserByUserEmail(String userEmail);
    UserDTO updateInfoUser(UserDTO user);
    String changePassword(String userEmail, String currentPassword, String newPassword);
}
