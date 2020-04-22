package com.microserviceitems.microserviceitems.model;

import com.app.common.models.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private Product products;
    private Integer quantity;

    public Double getTotal() {
        return products.getPrice() * quantity.doubleValue();
    }
}
