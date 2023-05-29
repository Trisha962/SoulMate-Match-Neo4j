package com.example.UserService.service;

import com.example.UserService.domain.User;
import com.example.UserService.exeption.UserAlreadyExistsException;
import com.example.UserService.exeption.UserNotFoundException;
import com.example.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }
//
//    @Override
//    public User checkLogin(User user) {
//        return  userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
//    }
//@Override
//public User registerUser(User user) throws UserAlreadyExistsException {
//    if (userRepository.findByEmail(user.getEmail()) != null) {
//        throw new UserAlreadyExistsException();
//    }
//
//    return userRepository.save(user);
//}

    @Override
    public User checkLogin(User user) throws UserNotFoundException {
        User retrievedUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (retrievedUser == null) {
            throw new UserNotFoundException();
        }

        return retrievedUser;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
