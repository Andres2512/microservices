package com.microservices.microservicesproducts.controller;

import com.app.common.models.entity.Product;
import com.microservices.microservicesproducts.models.service.IProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        Product productActual = productService.findById(id);
        productActual.setName(product.getName());
        productActual.setPrice(product.getPrice());

        return productService.save(productActual);

    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
