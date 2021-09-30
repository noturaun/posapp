package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Product;

public interface ProductRepository {
    Product[] getAll();
    void add(Product product);
    Product get(Integer productId);
    Boolean delete(Integer productId);
}
