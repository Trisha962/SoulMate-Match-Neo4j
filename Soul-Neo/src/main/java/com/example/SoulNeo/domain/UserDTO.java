package com.example.SoulNeo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserDTO {
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