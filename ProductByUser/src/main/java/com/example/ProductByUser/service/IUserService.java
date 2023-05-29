package com.example.ProductByUser.service;

import com.example.ProductByUser.exception.UserAlreadyExists;
import com.example.ProductByUser.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public User addUser(User user) throws UserAlreadyExists;
    public User getUsers(String email);

    Optional<User> findByName(String name);

    List<User> searchUsersByName(String name);

//    public Optional<User> findByName(String name);

}
