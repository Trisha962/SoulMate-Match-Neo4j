package com.productAuth.ProductByUser.service;

import com.productAuth.ProductByUser.model.User;

import java.util.List;

public interface IUserService {

    public User addUser(User user);
    public User getUsers(String email);

}
