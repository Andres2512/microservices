package com.microserviceitems.microserviceitems.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private Products products;
    private Integer quantity;

    public Double getTotal() {
        return products.getPrice() * quantity.doubleValue();
    }
}
