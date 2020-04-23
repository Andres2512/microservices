package com.microservice.serviceoauth.client;

import com.microservice.commonsuser.models.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@FeignClient(name = "service-users")
public interface UserFeignClient {

    @GetMapping("/user/search/find-user")
    public User findByUsername(@RequestParam String username);

    @PutMapping("user/{id}")
    public User update (@PathVariable("id")Long id, @RequestBody User user);


}
