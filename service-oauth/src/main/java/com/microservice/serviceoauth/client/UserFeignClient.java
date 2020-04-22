package com.microservice.serviceoauth.client;

import com.microservice.commonsuser.models.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-users")
public interface UserFeignClient {

    @GetMapping("/user/search/find-user")
    public User findByUsername(@RequestParam String username);

}
