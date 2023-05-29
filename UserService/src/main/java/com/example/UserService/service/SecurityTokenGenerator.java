package com.example.UserService.service;



import com.example.UserService.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    //The generateToken method takes a User object as a parameter
    // and returns a Map of strings that represents the generated token.

    public Map<String ,String> generateToken(User user);    //this method returns map obj
}