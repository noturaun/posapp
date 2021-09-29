package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Customer;

public class CustomerRepositoryImpl implements CustomerRepository{
    @Override
    public Customer[] getAll() {
        return new Customer[0];
    }

    @Override
    public Customer get(Integer customerId) {
        return null;
    }

    @Override
    public void create(Customer customer) {

    }

    @Override
    public void update(Integer customerId, Customer customer) {

    }

    @Override
    public void delete(Integer customerId) {

    }
}
