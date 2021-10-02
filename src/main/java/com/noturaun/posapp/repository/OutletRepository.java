package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Outlet;

public interface OutletRepository {
    Outlet[] getAll();
    Outlet get(Integer outletId);
    void add(Outlet outlet);
    void update(Integer outletId, Outlet changes);
    boolean delete(Integer outletId);
}
