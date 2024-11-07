package com.lyj.securitydomo.service;

import com.lyj.securitydomo.domain.User;
import com.lyj.securitydomo.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDto);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
}
