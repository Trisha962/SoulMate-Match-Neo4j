package com.example.ProductByUser.proxy;

import com.example.ProductByUser.model.UserDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@FeignClient(name = "neo-service", url = "http://localhost:2222")
public interface NeoProxy {

    @PostMapping("api/neo/v1/saveUser")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDto);
}