package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.InventoryControl;

public interface InventoryControlRepository {
    InventoryControl[] getAll();
    InventoryControl get(Integer inventoryControlId);
    void update(Integer inventoryControlId, InventoryControl changes);
    void delete(Integer inventoryControlId);
}
