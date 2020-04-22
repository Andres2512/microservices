package com.microservices.microservicesproducts.models.repository;

import com.app.common.models.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepositories extends CrudRepository<Product, Long> {

}
