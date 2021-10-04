package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Payment;

public interface PaymentRepository {
    Payment[] getAll();
    Payment get(Integer paymentId);
    void create(Payment payment);
    void update(Integer paymentId, Payment changes);
    boolean delete(Integer paymentId);
}
