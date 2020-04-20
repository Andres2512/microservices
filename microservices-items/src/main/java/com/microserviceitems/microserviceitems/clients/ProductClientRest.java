package com.microserviceitems.microserviceitems.clients;

import com.microserviceitems.microserviceitems.model.Products;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name ="service-products")
public interface ProductClientRest {

    @GetMapping("/list")
    List<Products> list();

    @GetMapping("details/{id}")
    Products details(@PathVariable Long id);
}
