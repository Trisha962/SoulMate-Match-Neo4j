package com.example.NotificationService.service;



import com.example.NotificationService.config.UserDTO;
import com.example.NotificationService.domain.Notification;

public interface NotificationService {

    public Notification getNotification(String email);

     public    void saveNotification(UserDTO userDTO);




}
