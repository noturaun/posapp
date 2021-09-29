package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Outlet;

public interface OutletRepository {
    Outlet[] getAll();
    void add(Outlet outlet);
    void updte(Integer outletId, Outlet changes);
    void delete(Integer outletId);
}
