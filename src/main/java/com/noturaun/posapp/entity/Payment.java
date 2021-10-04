package com.noturaun.posapp.entity;

public class Payment {
    Integer id;
    Integer paymentMethodId;
    String status;

    public Payment() {
    }

    public Payment(Integer id, Integer paymentMethodId, String status) {
        this.id = id;
        this.paymentMethodId = paymentMethodId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
