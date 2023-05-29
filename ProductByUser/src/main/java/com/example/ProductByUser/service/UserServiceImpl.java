package com.productAuth.ProductByUser.service;

import com.productAuth.ProductByUser.model.User;
import com.productAuth.ProductByUser.model.UserDto;
import com.productAuth.ProductByUser.proxy.UserProxy;


import com.productAuth.ProductByUser.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{
    private UserRepository userRepository;
    private UserProxy userProxy;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProxy userProxy) {
        this.userRepository = userRepository;
        this.userProxy = userProxy;
    }

    @Override
    public User addUser(User user) {
        String password= user.getPassword();
        user.setPassword(null);
        if(userRepository.findById(user.getEmail()).isEmpty()) {
            UserDto userDto = new UserDto();

            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());
            userDto.setRole(user.getRole());
            userDto.setAge(user.getAge());
            userDto.setGender(user.getGender());
            userDto.setCity(user.getCity());
            userDto.setImage(user.getImage());
            userProxy.registerUser(userDto);
        }
        return userRepository.save(user);
    }
//    @Override
//    public SoulMateUser addUser(SoulMateUser user) {
//        String userPassword = user.getUserPassword(); // Extract the user password from the user object
//
//        // Remove the userPassword from the user object before storing it in the MongoDB repository
//        user.setUserPassword(null);
//
//        if (soulMateUserRepo.findById(user.getUserEmail()).isEmpty()) {
//            // Create a UserDTO object to send to the proxy service
//            UserDTO userDTO = new UserDTO();
//            userDTO.setUserEmail(user.getUserEmail());
//            userDTO.setUserName(user.getUserName());
//            userDTO.setUserCity(user.getUserCity());
//            userDTO.setUserGender(user.getUserGender());
//            userDTO.setUserPassword(userPassword); // Use the extracted user password
//            userDTO.setUserPic(user.getUserPic());
//            userDTO.setUserRole(user.getUserRole());
//            userDTO.setUserAge(user.getUserAge());
//
//            // Call the registerUser() method in the proxy service
//            iSoulMateProxy.registerUser(userDTO);
//        }
//
//        // Save the user in the MongoDB repository
//        return soulMateUserRepo.save(user);
//    }

    @Override
    public User getUsers(String email) {
        return userRepository.findById(email).get();
    }
}

