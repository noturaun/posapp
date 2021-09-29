package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Product;
import com.zaxxer.hikari.HikariDataSource;

public class ProductRepositoryImpl implements ProductRepository {

    private HikariDataSource dataSource;

    @Override
    public Product[] getAll() {
        return new Product[0];
    }

    @Override
    public void add() {

    }

    @Override
    public Product get(String name) {
        return null;
    }

    @Override
    public void delete() {

    }
}
