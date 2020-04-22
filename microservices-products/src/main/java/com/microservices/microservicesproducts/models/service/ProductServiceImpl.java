package com.microservices.microservicesproducts.models.service;

import com.app.common.models.entity.Product;
import com.microservices.microservicesproducts.models.repository.ProductRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepositories productRepositories;

    public ProductServiceImpl(ProductRepositories productRepositories) {
        this.productRepositories = productRepositories;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) productRepositories.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepositories.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepositories.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productRepositories.deleteById(id);
    }
}
