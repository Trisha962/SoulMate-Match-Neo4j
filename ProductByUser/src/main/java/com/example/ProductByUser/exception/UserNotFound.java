package com.example.ProductByUser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code= HttpStatus.CONFLICT, reason = "User not found")
public class  UserNotFound  extends Exception{
}
