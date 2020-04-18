package com.microservices.microservicesproducts.models.service;

import com.microservices.microservicesproducts.models.entity.Product;

import java.util.List;

public interface IProductService {

    public List<Product> findAll();

    public Product findById(Long id);
}
