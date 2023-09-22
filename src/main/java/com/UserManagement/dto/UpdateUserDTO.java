package com.UserManagement.dto;

import com.UserManagement.validation.Compare;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Compare(message = "Password is not matched", firstField = "password", secondField = "PasswordConfirmation")
public class UpdateUserDTO {

    private Long Id;

    @NotBlank(message = "Username is required")
    @Size(max = 20, message = "Username can't be more than 20 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(max = 20, message = "Password can't be more than 20 characters")
    private String password;

    @NotBlank(message = "Password is required")
    @Size(max = 20, message = "Password can't be more than 20 characters")
    private String passwordConfirmation;

    public UpdateUserDTO(Long Id,String username, String password, String passwordConfirmation) {
        this.Id = Id;
        this.username = username;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

    public UpdateUserDTO(Long id, String username, String password) {
        Id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
