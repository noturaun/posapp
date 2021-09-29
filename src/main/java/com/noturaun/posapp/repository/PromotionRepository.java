package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Promotion;

public interface PromotionRepository {
    Promotion[] getAll();
    Promotion get();
    void create(Promotion promotion);
    void update(Integer promotionId, Promotion changes);
    void delete(Integer promotionId);
}
