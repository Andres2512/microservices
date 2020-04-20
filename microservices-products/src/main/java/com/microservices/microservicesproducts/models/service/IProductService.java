package com.microservices.microservicesproducts.models.service;

import com.microservices.microservicesproducts.models.entity.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll();

    Product findById(Long id);
}
