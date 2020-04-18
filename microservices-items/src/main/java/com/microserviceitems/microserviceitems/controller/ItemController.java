package com.microserviceitems.microserviceitems.controller;

import com.microserviceitems.microserviceitems.model.Item;
import com.microserviceitems.microserviceitems.model.Products;
import com.microserviceitems.microserviceitems.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(@Qualifier("serviceFeign") ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping("/list")
    public List<Item> list() {
        return itemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "alterDetails")
    @GetMapping("/details/{id}/quantity/{quantity}")
    public Item details(@PathVariable Long id, @PathVariable Integer quantity) {
        return itemService.findById(id, quantity);
    }

    public Item alterDetails (@PathVariable Long id, @PathVariable Integer quantity){
        Item item = new Item();
        Products products = new Products();

        item.setQuantity(quantity);
        products.setId(id);
        products.setName("Sonic");
        products.setPrice(200.00);

        item.setProducts(products);

        return item;

    }
}
