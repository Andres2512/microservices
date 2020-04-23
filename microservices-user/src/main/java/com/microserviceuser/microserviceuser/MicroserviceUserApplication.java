package com.microserviceuser.microserviceuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.microservice.commonsuser.models.entity"})
public class MicroserviceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceUserApplication.class, args);
    }

}
