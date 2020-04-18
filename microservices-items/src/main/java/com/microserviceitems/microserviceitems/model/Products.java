package com.microserviceitems.microserviceitems.model;

import lombok.Data;

import java.util.Date;

@Data
public class Products {
    private Long id;
    private String name;
    private Double price;
    private Date createAt;
    private Integer port;
}
