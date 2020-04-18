package com.microserviceitems.microserviceitems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@RibbonClient(name = "services-products")
@EnableCircuitBreaker
@EnableFeignClients
@SpringBootApplication
public class MicroserviceItemsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceItemsApplication.class, args);
    }

}
