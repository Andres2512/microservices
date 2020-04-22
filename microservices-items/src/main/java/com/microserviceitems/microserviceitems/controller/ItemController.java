package com.microserviceitems.microserviceitems.controller;

import com.app.common.models.entity.Product;
import com.microserviceitems.microserviceitems.model.Item;
import com.microserviceitems.microserviceitems.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
public class ItemController {

    private static Logger log = LoggerFactory.getLogger(ItemController.class);

    private final Environment env;

    @Value("${configuration.text}")
    private String text;

    private final ItemService itemService;

    public ItemController(@Qualifier("serviceFeign") ItemService itemService, Environment env) {
        this.itemService = itemService;
        this.env = env;
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

    public Item alterDetails(@PathVariable Long id, @PathVariable Integer quantity) {
        Item item = new Item();
        Product product = new Product();

        item.setQuantity(quantity);
        product.setId(id);
        product.setName("Sonic");
        product.setPrice(200.00);

        item.setProducts(product);

        return item;

    }

    @GetMapping("/get-config")
    public ResponseEntity<?> getConfig(@Value("${server.port}") String puerto) {

        log.info(text);

        Map<String, String> json = new HashMap<>();
        json.put("text", text);
        json.put("puerto", puerto);

        if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
            json.put("author.name", env.getProperty("configuration.author.name"));
            json.put("author.email", env.getProperty("configuration.author.email"));
        }

        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return itemService.save(product);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@RequestBody Product product, @PathVariable Long id) {
        return itemService.update(product, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }
}
