package com.example.ecommerceweek6.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private String productName;
    private String productCategory;
    private double price;
    private int quantity;
    private String product_image;
}
