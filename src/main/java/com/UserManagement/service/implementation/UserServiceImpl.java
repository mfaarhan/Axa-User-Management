package com.UserManagement.service.implementation;

import com.UserManagement.dto.InsertUserDTO;
import com.UserManagement.dto.UpdateUserDTO;
import com.UserManagement.entity.User;
import com.UserManagement.repository.UserRepository;
import com.UserManagement.service.abstraction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(InsertUserDTO dto) {
        User user = new User(
            dto.getUsername(),
            dto.getPassword()
        );

        userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        User tempStudent = null;
        if (user.isPresent()){
            tempStudent = user.get();
        }

        return tempStudent;
    }

    @Override
    public User updateUser(UpdateUserDTO dto) {
        User user = findUserById(dto.getId());

        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        return userRepository.save(user);
    }

    @Override
    public User deleteUserById(Long id) {
        User user = findUserById(id);

        if (user != null){
            userRepository.delete(user);
        }
        return user;
    }
}
