package com.microservices.microservicesproducts.controller;

import com.microservices.microservicesproducts.models.entity.Product;
import com.microservices.microservicesproducts.models.service.IProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
public class ProductController {

    @Value("${server.port}")
    private Integer port;


    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/list")
    public List<Product> list() {
        return productService.findAll().stream().peek(p -> p.setPort(port)).collect(Collectors.toList());
    }

    @GetMapping(value = "/details/{id}")
    public Product details(@PathVariable Long id) {
        Product product = productService.findById(id);
        product.setPort(port);
        return product;
    }
}
