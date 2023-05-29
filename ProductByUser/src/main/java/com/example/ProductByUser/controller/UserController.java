package com.example.ProductByUser.controller;

import com.example.ProductByUser.exception.UserAlreadyExists;
import com.example.ProductByUser.model.User;
import com.example.ProductByUser.repository.UserRepository;

import com.example.ProductByUser.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/soulmate/mongodb/v1")
public class UserController {
    private IUserService iUserService;
    private UserRepository userRepository;
    @Autowired
    public UserController(IUserService iUserService, UserRepository userRepository) {
        this.iUserService = iUserService;
        this.userRepository = userRepository;
    }



//    @PostMapping("/addUser")
//    public ResponseEntity addUser(@RequestBody User user){
//        System.out.println(iUserService.addUser(user));
//        return new ResponseEntity(iUserService.addUser(user), HttpStatus.CREATED);
//    }
@PostMapping("/addUser")
public ResponseEntity<?> addUser(@RequestBody User user) {
    try {
        User addedUser = iUserService.addUser(user);
        return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    } catch (UserAlreadyExists e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
    }
}



    @GetMapping("/getUser")//represents the HTTP request being made-servletrequest
    public ResponseEntity getUsersInformation(HttpServletRequest httpServletRequest) {
        String email = (String) httpServletRequest.getAttribute("attr1");
        System.out.println("*****************" + email);
        return new ResponseEntity(iUserService.getUsers(email), HttpStatus.OK);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable String name) {
        Optional<User> userOptional = iUserService.findByName(name);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchUsersByName(@RequestParam("name") String name) {
        List<User> users = iUserService.searchUsersByName(name);

        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}