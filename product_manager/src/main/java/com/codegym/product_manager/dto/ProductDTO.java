package com.codegym.product_manager.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ProductDTO {
    private int id;
    private String title;
    private BigDecimal price;
    private int quantity;
    private Date updatedAt;
    private Date createdAt;
    private String name;
    private int size;
    private String color;
    private String img;

    public ProductDTO() {
    }
    public ProductDTO(int id, String title, BigDecimal price, int quantity, Date updatedAt, Date createdAt, String name, int size, String color, String img) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.name = name;
        this.size = size;
        this.color = color;
        this.img = img;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", idCategoryName='" + name + '\'' +
                ", size=" + size +
                ", color='" + color + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}

