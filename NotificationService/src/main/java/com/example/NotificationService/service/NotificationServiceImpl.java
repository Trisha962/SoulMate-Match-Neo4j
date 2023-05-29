package com.example.NotificationService.service;

import com.example.NotificationService.config.UserDTO;
import com.example.NotificationService.domain.Notification;
import com.example.NotificationService.repository.NotificationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements  NotificationService{

    private NotificationRepository notificationRepository;
    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification getNotification(String email) {
        return notificationRepository.findById(email).get();
    }


        @RabbitListener(queues = "SoulQueue")
        @Override
        public void saveNotification(UserDTO userDTO) {
            //object of notification
            Notification notification=new Notification();
            //fetch email form userDTO object
            String email= userDTO.getJsonObject().get("email").toString();
            if(notificationRepository.findById(email).isEmpty()){
                notification.setEmail(email);
            }
            notification.setNotificationMessage("list of people");
            notification.setJsonObject(userDTO.getJsonObject());
            notificationRepository.save(notification);

        }
    }

