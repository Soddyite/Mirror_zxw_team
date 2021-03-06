package com.example.dllo.mirror.model.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "ADDRESS".
 */
public class Address {

    private Long id;
    private String name;
    private String num;
    private String address;

    public Address() {
    }

    public Address(Long id) {
        this.id = id;
    }

    public Address(Long id, String name, String num, String address) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
