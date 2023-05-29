package com.example.UserService.service;

import com.example.UserService.domain.User;
import com.example.UserService.exeption.UserAlreadyExistsException;
import com.example.UserService.exeption.UserNotFoundException;

import java.util.List;

public interface UserService {
    public User registerUser(User user);
    public User checkLogin(User user) throws UserNotFoundException;

    public List<User> getAllUsers();
}
