package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Customer;

public interface CustomerRepository {
    Customer[] getAll();
    Customer get(Integer customerId);
    void create(Customer customer);
    void update(Integer customerId, Customer customer);
    void delete(Integer customerId);
}
