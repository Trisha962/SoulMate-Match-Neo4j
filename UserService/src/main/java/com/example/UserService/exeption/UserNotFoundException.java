package com.example.UserService.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(code= HttpStatus.CONFLICT, reason = "Customer already exists")
public class  UserNotFoundException  extends Exception{
}

