package com.productAuth.ProductByUser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class UserDto {
    @Id
    private String email;
    private String name;
    private String gender;
    private String city;
    private int age;
    private  String role;
    private String image;
    private String password;

}