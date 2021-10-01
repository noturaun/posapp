package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Product;

public interface ProductRepository {
    Product[] getAll();
    void add(Product product);
    Product get(Integer productId);
    void update(Integer productId, Product changes);
    Boolean delete(Integer productId);
}
