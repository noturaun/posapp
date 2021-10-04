package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.InventoryControl;

public interface InventoryControlRepository {
    InventoryControl[] getAll();
    InventoryControl get(Integer productId);
    void add(InventoryControl inventoryControl);
    void update(Integer inventoryControlId, Integer newStock);
    Boolean delete(Integer inventoryControlId);
}
