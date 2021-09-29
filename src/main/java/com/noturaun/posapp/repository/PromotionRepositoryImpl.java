package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Promotion;

public class PromotionRepositoryImpl implements PromotionRepository{
    @Override
    public Promotion[] getAll() {
        return new Promotion[0];
    }

    @Override
    public Promotion get() {
        return null;
    }

    @Override
    public void create(Promotion promotion) {

    }

    @Override
    public void update(Integer promotionId, Promotion changes) {

    }

    @Override
    public void delete(Integer promotionId) {

    }
}
