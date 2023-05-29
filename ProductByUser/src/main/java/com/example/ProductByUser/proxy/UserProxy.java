package com.example.ProductByUser.proxy;

import com.example.ProductByUser.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@Service
@FeignClient(name = "gateway-server", url = "http://localhost:7000")
public interface UserProxy {

    @PostMapping("/api/soulmate/v1/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto);
}
