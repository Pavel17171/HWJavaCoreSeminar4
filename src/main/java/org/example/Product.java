package org.example;

import java.util.Map;

public class Product {
    static int nextProductId = 1;
    private int productId;
    private String title;
    private int price;
    private static Map<Integer, Product> productMap;


    public Product(String title, int price) {
        this.productId = nextProductId++;
        this.title = title;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Product. ID: " + productId
               + ". Title: " + title
               + ", price: " + price;
    }
}