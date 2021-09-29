package com.noturaun.posapp.entity;

public class Payment {
    Integer id;
    String name;
    String desc;
    String status;

    public Payment() {
    }

    public Payment(Integer id, String name, String desc, String status) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
