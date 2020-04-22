package com.microserviceuser.microserviceuser.models.repository;

import com.microservice.commonsuser.models.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path="user")
public interface UserRepository extends PagingAndSortingRepository<User,Long> {

    @RestResource(path="find-user")
    public User findByUsername(@Param("username") String username);


}
