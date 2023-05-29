package com.example.SoulNeo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Interest {
    @Id
    private String email;
    private String name;
    private String gender;
    private String city;
    private Integer age;
    private String password;
    private  String role;
    private String image;

}
