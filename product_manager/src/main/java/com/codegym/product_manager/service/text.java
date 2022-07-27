package com.codegym.product_manager.service;

import com.codegym.product_manager.model.Product;

import java.util.List;

public class text {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        List<Product> products = productService.findAll();
        for(Product item : products ){
            System.out.println(item);
        }
    }
}

