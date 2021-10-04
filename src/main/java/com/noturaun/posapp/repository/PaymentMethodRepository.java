package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.PaymentMethod;

public interface PaymentMethodRepository {
    PaymentMethod[] getAll();
    PaymentMethod get(Integer id);
    void add(PaymentMethod paymentMethod);
    void update(Integer paymentMethodId, PaymentMethod changes);
    boolean delete(Integer paymentMethodId);
}
