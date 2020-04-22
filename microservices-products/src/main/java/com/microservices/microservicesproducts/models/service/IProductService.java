package com.microservices.microservicesproducts.models.service;


import com.app.common.models.entity.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll();

    Product findById(Long id);

    public Product save(Product product);

    void deleteById(Long id);

}
