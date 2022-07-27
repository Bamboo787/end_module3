package com.codegym.product_manager.model;

import java.util.Date;

public class User {
    private int id;
    private String username;
    private String password;
    private String fullname;
    private String phone;
    private String email;
    private int cityId;
    private String role;
    private Date updatedAt;
    private Date createdAt;
    private String img;

    public User(int id, String username, String password, String fullname, String phone, String email, int cityId, String role, Date updatedAt, Date createdAt, String img) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.cityId = cityId;
        this.role = role;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.img = img;
    }

    public User(String username, String password, String fullname, String phone, String email, int cityId, String role) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.cityId = cityId;
        this.role = role;
    }

    public User(String username, String password, String fullname, String phone, String email, int cityId, String role, String img) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.cityId = cityId;
        this.role = role;
        this.img = img;
    }

    public User(int id, String username, String password, String fullname, String phone, String email, int cityId, String role, String img) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.cityId = cityId;
        this.role = role;
        this.img = img;
    }


    public User(String fullname, String email) {
        this.fullname = fullname;
        this.email = email;
    }

    public User(String username, String password, String fullname, String phone, String email, int cityId, String role, Date updatedAt, Date createdAt, String img) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.cityId = cityId;
        this.role = role;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.img = img;
    }

    public User(String username, String password, String fullname, String phone, String email, String role, Date updatedAt, Date createdAt, String img) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", cityId='" + cityId + '\'' +
                ", role='" + role + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
