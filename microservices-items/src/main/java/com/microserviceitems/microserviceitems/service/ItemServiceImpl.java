package com.microserviceitems.microserviceitems.service;

import com.microserviceitems.microserviceitems.model.Item;
import com.microserviceitems.microserviceitems.model.Products;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService{

    private final RestTemplate clientRest;

    public ItemServiceImpl(RestTemplate clientRest) {
        this.clientRest = clientRest;
    }

    @Override
    public List<Item> findAll() {
        List<Products> products = Arrays.asList(Objects.requireNonNull(clientRest.getForObject("http://service-products/list", Products[].class)));
        return products.stream().map(p-> new Item(p,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        Map<String,String> pathVariables= new HashMap<>();
        pathVariables.put("id",id.toString());
        Products products= clientRest.getForObject("http://service-products/details/{id}",Products.class,pathVariables);
        return new Item(products,quantity);
    }
}
