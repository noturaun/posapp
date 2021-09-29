package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Payment;

public class PaymentRepositoryImpl implements PaymentRepository{
    @Override
    public Payment[] getAll() {
        return new Payment[0];
    }

    @Override
    public Payment get(Integer paymentId) {
        return null;
    }

    @Override
    public void create(Payment payment) {

    }

    @Override
    public void update(Integer paymentId, Payment changes) {

    }

    @Override
    public void delete(Integer paymentId) {

    }
}
