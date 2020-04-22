package com.microserviceitems.microserviceitems.service;

import com.app.common.models.entity.Product;
import com.microserviceitems.microserviceitems.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> findAll();

    Item findById(Long id, Integer quantity);

    Product save(Product product);

    Product update(Product product, Long id);

    void delete(Long id);

}
