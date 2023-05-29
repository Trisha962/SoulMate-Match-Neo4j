package com.example.NotificationService.controller;

import com.example.NotificationService.config.UserDTO;
import com.example.NotificationService.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/notification/v1")
public class NotificationController {

    private NotificationService notificationService;
    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    @GetMapping("/notification/{email}")
    public ResponseEntity<?> getNotification(@PathVariable String email){
        return new ResponseEntity<>(notificationService.getNotification(email), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> saveNotification(@RequestBody UserDTO userDTO) {
        notificationService.saveNotification(userDTO);
        return new ResponseEntity<>("Notification saved successfully", HttpStatus.CREATED);
    }

}
