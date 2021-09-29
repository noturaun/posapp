package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.InventoryControl;

public class InventoryControlRepositoryImpl implements InventoryControlRepository{
    @Override
    public InventoryControl[] getAll() {
        return new InventoryControl[0];
    }

    @Override
    public InventoryControl get(Integer inventoryControlId) {
        return null;
    }

    @Override
    public void update(Integer inventoryControlId, InventoryControl changes) {

    }

    @Override
    public void delete(Integer inventoryControlId) {

    }
}
