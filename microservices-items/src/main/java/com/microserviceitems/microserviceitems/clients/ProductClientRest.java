package com.microserviceitems.microserviceitems.clients;

import com.app.common.models.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "service-products")
public interface ProductClientRest {

    @GetMapping("/list")
    List<Product> list();

    @GetMapping("details/{id}")
    Product details(@PathVariable Long id);

    @PostMapping("/create")
    Product create(@RequestBody Product product);


    @PutMapping("/edit/{id}")
    Product update(@RequestBody Product product, @PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id);
}
