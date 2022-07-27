package com.codegym.product_manager.model;

public class Catogory {
    private int id;
    private String name;

    public Catogory() {
    }

    public Catogory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Catogory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
