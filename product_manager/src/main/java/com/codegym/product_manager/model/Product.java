package com.codegym.product_manager.model;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private int id;
    private String title;
    private BigDecimal price;
    private int quantity;
    private Date updatedAt;
    private Date createdAt;
    private int idCategory;
    private int size;
    private String color;
    private String img;

    public Product() {
    }

    public Product(int id, String title, BigDecimal price, int quantity, Date updatedAt, Date createdAt, int idCategory, int size, String color, String img) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.idCategory = idCategory;
        this.size = size;
        this.color = color;
        this.img = img;
    }

    public Product(String title, BigDecimal price, int quantity, int idCategory, int size, String color, String img) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.idCategory = idCategory;
        this.size = size;
        this.color = color;
        this.img = img;
    }

    public Product(int id, String title, BigDecimal price, int quantity, int idCategory, int size, String color, String img) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.idCategory = idCategory;
        this.size = size;
        this.color = color;
        this.img = img;
    }

    public Product(String title, BigDecimal valueOf, int quantity, int idCategory, int size, String color) {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String  toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", quantity='" + quantity + '\'' +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", idCategory=" + idCategory +
                ", size=" + size +
                ", color='" + color + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}


