package com.noturaun.posapp.entity;

public class Promotion {
    Integer id;
    String promoCode;
    String status;
    Double discount;

    public Promotion() {
    }

    public Promotion(Integer id, String promoCode, String status, Double discount) {
        this.id = id;
        this.promoCode = promoCode;
        this.status = status;
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
