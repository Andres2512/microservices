package com.microserviceitems.microserviceitems.service;

import com.app.common.models.entity.Product;
import com.microserviceitems.microserviceitems.model.Item;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {

    private final RestTemplate clientRest;

    public ItemServiceImpl(RestTemplate clientRest) {
        this.clientRest = clientRest;
    }

    @Override
    public List<Item> findAll() {
        List<Product> product = Arrays.asList(Objects.requireNonNull(clientRest.getForObject("http://service-products/list", Product[].class)));
        return product.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        Product product = clientRest.getForObject("http://service-products/details/{id}", Product.class, pathVariables);
        return new Item(product, quantity);
    }

    @Override
    public Product save(Product product) {
        HttpEntity<Product> body = new HttpEntity<Product>(product);
        ResponseEntity<Product> response = clientRest.exchange("http://service-products/create", HttpMethod.POST, body, Product.class);
        Product responseProduct = response.getBody();
        return responseProduct;
    }

    @Override
    public Product update(Product product, Long id) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        HttpEntity<Product> body = new HttpEntity<Product>(product);
        ResponseEntity<Product> response = clientRest.exchange("http://service-products/edit/{id}", HttpMethod.PUT, body, Product.class, pathVariables);
        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        clientRest.delete("http://service-products/delete/{id}", pathVariables);
    }
}
