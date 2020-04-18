package com.microserviceitems.microserviceitems.service;

import com.microserviceitems.microserviceitems.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> findAll();
    Item findById (Long id, Integer quantity);

}
