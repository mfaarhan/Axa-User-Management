package com.UserManagement.service.abstraction;

import com.UserManagement.dto.InsertUserDTO;
import com.UserManagement.dto.UpdateUserDTO;
import com.UserManagement.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    void addUser(InsertUserDTO dto);

    User findUserById(Long id);

    User updateUser(UpdateUserDTO dto);

    User deleteUserById(Long id);


}
