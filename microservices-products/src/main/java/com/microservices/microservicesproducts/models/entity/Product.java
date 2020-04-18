package com.microservices.microservicesproducts.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "products")
public class Product implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @Transient
    private Integer port;

    private static final long serialVersionUID = 1L;

}
