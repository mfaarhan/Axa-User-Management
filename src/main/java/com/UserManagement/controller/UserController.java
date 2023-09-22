package com.UserManagement.controller;

import com.UserManagement.dto.InsertUserDTO;
import com.UserManagement.dto.UpdateUserDTO;
import com.UserManagement.entity.User;
import com.UserManagement.service.abstraction.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<User> findAllUsers () {
        List<User> users = userService.findAllUsers();

        log.info("Displaying all Users ");
        return users;
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@Valid @RequestBody InsertUserDTO dto, BindingResult bindingResult){
        try{
            if(!bindingResult.hasErrors()){
                userService.addUser(dto);
                log.info("Creating a User with Username : "+dto.getUsername());
                return new ResponseEntity<>(dto, HttpStatus.CREATED);
            }else{
                log.info("Failed Creating a User");
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Username or Password Not Valid");
            }
        }catch (Exception exception){
            log.error("There is a Run-time Exception "+exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }


    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UpdateUserDTO dto,BindingResult bindingResult){
        try{
            if(!bindingResult.hasErrors()){
                User user = userService.updateUser(dto);
                log.info("Update a User with Id : "+dto.getId());
                return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
            }else{
                log.info("Failed Updating a User");
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Username or Password Not Valid");
            }
        }catch(Exception exception){
            log.error("There is a Run-time Exception "+exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long Id){

        User user = userService.deleteUserById(Id);

        if (user == null){
            log.info("Failed Deleting a User");
            return new ResponseEntity<>("User with ID " + Id + " not found", HttpStatus.NOT_FOUND);
        }

        log.info("Delete a User with Id : "+Id);
        return new ResponseEntity<>("User with ID " + Id + " has been deleted", HttpStatus.OK);
    }
}
