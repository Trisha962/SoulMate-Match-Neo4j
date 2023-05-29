package com.example.UserService.service;

import com.example.UserService.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator{
    @Override
    public Map<String, String> generateToken(User user) {
        System.out.println(user);
        //creates a new HashMap called result, which will be
        // used to store the generated token
        Map<String,String> result= new HashMap<>();
        //creates a new HashMap called userdata, which will be used to store
        // the user data that will be included in the token.
        Map<String,Object> userdata= new HashMap<>();
        //add properties into userdata
        userdata.put("email",user.getEmail() );
        userdata.put("image",user.getImage() );
        userdata.put("role",user.getRole());
        userdata.put("age",user.getAge());
        userdata.put("city", user.getCity());
        userdata.put("name",user.getName());
        userdata.put("gender", user.getGender());
        //The Jwts.builder() method starts building the token
        String myToken= Jwts.builder().setClaims(userdata)
                //setIssuedAt sets the issue time of the token to the current time.
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512,"SoulKey")
                // used to generate the final token as a compact string.
                .compact();
        result.put("Token",myToken);
        result.put("email", user.getEmail());
        result.put("Message","login successful");
        result.put("Role", user.getRole());
        result.put("gender",user.getGender());
        result.put("image", user.getImage());
        result.put("city", user.getCity());

        return result;
    }
    }

