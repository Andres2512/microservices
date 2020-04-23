package com.microservice.serviceoauth.services;

import com.microservice.commonsuser.models.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUserService {

    public User findByUserName(String username);

    public User update (Long id, User user);
}
