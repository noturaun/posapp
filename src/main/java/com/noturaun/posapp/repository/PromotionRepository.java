package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Promotion;

public interface PromotionRepository {
    Promotion[] getAll();
    Promotion get(Integer promoId);
    void create(Promotion promotion);
    void update(Integer promotionId, Promotion changes);
    boolean delete(Integer promotionId);
}
