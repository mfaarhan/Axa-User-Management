package com.UserManagement;

import com.UserManagement.dto.InsertUserDTO;
import com.UserManagement.dto.UpdateUserDTO;
import com.UserManagement.entity.User;
import com.UserManagement.repository.UserRepository;
import com.UserManagement.service.abstraction.UserService;
import com.UserManagement.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("User1", "Password1"));
        userList.add(new User("User2", "Password2"));

        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.findAllUsers();

        assertEquals(2, result.size());
    }

    @Test
    public void testAddUser() {
        InsertUserDTO insertUserDTO = new InsertUserDTO("NewUser", "NewPassword","NewPassword");

        userService.addUser(insertUserDTO);

        verify(userRepository, times(1)).save(argThat(user ->
                user.getUsername().equals(insertUserDTO.getUsername()) &&
                        user.getPassword().equals(insertUserDTO.getPassword())));
    }

    @Test
    public void testFindUserById() {
        Long userId = 1L;
        User user = new User();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.findUserById(userId);

        assertEquals(user.getId(), result.getId());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getPassword(), result.getPassword());
    }

    @Test
    public void testUpdateUser() {
        UpdateUserDTO userUpdateDTO = new UpdateUserDTO(1L, "updateduser", "updatedpassword");
        User existingAccount = new User("testuser", "testpassword");
        existingAccount.setId(1L);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(existingAccount));
        when(userRepository.save(any(User.class))).thenReturn(existingAccount);

        User result = userService.updateUser(userUpdateDTO);

        assertEquals(userUpdateDTO.getUsername(), result.getUsername());
        assertEquals(userUpdateDTO.getPassword(), result.getPassword());
    }

    @Test
    public void testDeleteUserById() {
        Long userId = 1L;
        User user = new User();

        Optional<User> optionalUser = Optional.of(user);

        when(userRepository.findById(userId)).thenReturn(optionalUser);

        User result = userService.deleteUserById(userId);

        assertEquals(user, result);
    }
}