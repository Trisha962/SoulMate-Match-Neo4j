package com.example.UserService.controller;

import com.example.UserService.domain.User;
import com.example.UserService.exeption.UserNotFoundException;
import com.example.UserService.service.SecurityTokenGenerator;
import com.example.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/soulmate/v1")
public class UserController {
    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;
    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }


    //http://localhost:5555/api/soulmate/v1/register
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        System.out.println("reaches controller");

        user.setRole("User-Role");
        System.out.println(user);
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

    //http://localhost:5555/api/soulmate/v1/login
//    @PostMapping("/login")
//    public ResponseEntity loginUser(@RequestBody  User user){
//        User retrievedUser=userService.checkLogin(user);
//        System.out.println(retrievedUser);
//        if(retrievedUser!= null ){
//            System.out.println(retrievedUser);
//
//
//            return  new ResponseEntity(securityTokenGenerator.generateToken(retrievedUser),HttpStatus.OK);
//        }
//        else{
//            return new ResponseEntity<>("Authentication failed,Please try again!!",HttpStatus.EXPECTATION_FAILED);
//        }
//    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            User retrievedUser = userService.checkLogin(user);
            return new ResponseEntity<>(securityTokenGenerator.generateToken(retrievedUser), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
    //http://localhost:5555/api/soulmate/v1/allUsers
    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
