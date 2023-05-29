package com.productAuth.ProductByUser.controller;

import com.productAuth.ProductByUser.model.User;
import com.productAuth.ProductByUser.repository.UserRepository;

import com.productAuth.ProductByUser.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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



    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody User user) {
        System.out.println(iUserService.addUser(user));
        return new ResponseEntity(iUserService.addUser(user), HttpStatus.CREATED);
    }


    @GetMapping("/getUser")//represents the HTTP request being made-servletrequest
    public ResponseEntity getUsersInformation(HttpServletRequest httpServletRequest) {
        String email = (String) httpServletRequest.getAttribute("attr1");
        System.out.println("*****************" + email);
        return new ResponseEntity(iUserService.getUsers(email), HttpStatus.OK);
    }
}