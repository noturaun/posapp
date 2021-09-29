package com.noturaun.posapp.repository;

import com.noturaun.posapp.entity.Employee;

public interface EmployeeRepository {
    Employee[] getAll();
    Employee get(Integer employeeId);
    void add(Employee employee);
    void update(Integer employeeId, Employee changes);
    void delete(Integer employeeId);
}
