package com.example.NotificationService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT, reason = "Notification not found exception")
public interface NotificationNotFoundException{
}
