package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Enterprise;

public interface EnterpriseRepository {
    Enterprise get(Integer enterpriseId);
    void add(Enterprise enterprise);
    void update(Integer enterpriseId, Enterprise changes);
    void delete(Integer enterpriseId);
}
