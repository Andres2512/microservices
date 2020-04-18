package com.microserviceitems.microserviceitems.service;

import com.microserviceitems.microserviceitems.clients.ProductClientRest;
import com.microserviceitems.microserviceitems.model.Item;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
public class ItemServiceFeign implements ItemService {

    private final ProductClientRest clientFeign;

    public ItemServiceFeign(ProductClientRest clientFeign) {
        this.clientFeign = clientFeign;
    }


    @Override
    public List<Item> findAll() {
        return clientFeign.list().stream().map(p-> new Item(p,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        return new Item(clientFeign.details(id),quantity);
    }
}
