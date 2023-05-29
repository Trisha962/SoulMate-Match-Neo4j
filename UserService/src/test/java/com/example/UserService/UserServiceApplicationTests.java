package com.example.UserService;

import com.example.UserService.controller.UserController;
import com.example.UserService.domain.User;

import com.example.UserService.exeption.UserNotFoundException;
import com.example.UserService.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceApplicationTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_SuccessfulRegistration() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");

        when(userService.registerUser(any(User.class))).thenReturn(user);

        ResponseEntity<?> response = userController.registerUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user, response.getBody());
    }

//    @Test
//    void testCheckLogin_ValidCredentials() {
//        User user = new User();
//        user.setEmail("test@example.com");
//        user.setPassword("password123");
//
//        when(userService.checkLogin(any(User.class))).thenReturn(user);
//
//        ResponseEntity<?> response = userController.loginUser(user);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        // Additional assertion for the generated security token can be added here
//    }

    @Test
    void testCheckLogin_InvalidCredentials() throws UserNotFoundException {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("incorrectpassword");

        when(userService.checkLogin(any(User.class))).thenReturn(null);

        ResponseEntity<?> response = userController.loginUser(user);

        assertEquals(HttpStatus.EXPECTATION_FAILED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Authentication failed,Please try again!!", response.getBody());
    }

//    @Test
//    void testRegisterUser_MissingRequiredFields() {
//        User user = new User();
//        user.setEmail("test@example.com");
//
//        ResponseEntity<?> response = userController.registerUser(user);
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertNotNull(response.getBody());
//        // Additional assertion for the error message about missing required fields can be added here
//    }
//
//    @Test
//    void testCheckLogin_MissingRequiredFields() {
//        User user = new User();
//        user.setEmail("test@example.com");
//
//        ResponseEntity<?> response = userController.loginUser(user);
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertNotNull(response.getBody());
//        // Additional assertion for the error message about missing required fields can be added here
//    }
}
