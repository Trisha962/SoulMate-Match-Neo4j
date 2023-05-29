package com.example.ProductByUser.service;


import com.example.ProductByUser.exception.UserAlreadyExists;
import com.example.ProductByUser.model.User;
import com.example.ProductByUser.model.UserDTO;

import com.example.ProductByUser.proxy.NeoProxy;
import com.example.ProductByUser.proxy.UserProxy;
import com.example.ProductByUser.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{
    private UserRepository userRepository;
    private UserProxy userProxy;
    private NeoProxy neoProxy;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProxy userProxy, NeoProxy neoProxy) {
        this.userRepository = userRepository;
        this.userProxy = userProxy;
        this.neoProxy = neoProxy;
    }




//    @Override
//    public User addUser(User user) {
//        String password= user.getPassword();
//        user.setPassword(null);
//        if(userRepository.findById(user.getEmail()).isEmpty()) {
//            UserDTO userDto = new UserDTO();
//
//            userDto.setEmail(user.getEmail());
//            userDto.setPassword(password);
//            userDto.setRole(user.getRole());
//            userDto.setName(user.getName());
//            userDto.setAge(user.getAge());
//            userDto.setGender(user.getGender());
//            userDto.setCity(user.getCity());
//            userDto.setImage(user.getImage());
//            userProxy.registerUser(userDto);
//            neoProxy.registerUser(userDto);
//        }
//        return userRepository.save(user);
//
//    }

    @Override
    public User addUser(User user) throws UserAlreadyExists {
        String password = user.getPassword();
        user.setPassword(null);

        if (userRepository.findById(user.getEmail()).isPresent()) {
            throw new UserAlreadyExists();
        }

        UserDTO userDto = new UserDTO();
        userDto.setEmail(user.getEmail());
        userDto.setPassword(password);
        userDto.setRole(user.getRole());
        userDto.setName(user.getName());
        userDto.setAge(user.getAge());
        userDto.setGender(user.getGender());
        userDto.setCity(user.getCity());
        userDto.setImage(user.getImage());
        userProxy.registerUser(userDto);
        neoProxy.registerUser(userDto);

        return userRepository.save(user);
    }

    @Override
    public User getUsers(String email) {
        return userRepository.findById(email).get();
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> searchUsersByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }
}

