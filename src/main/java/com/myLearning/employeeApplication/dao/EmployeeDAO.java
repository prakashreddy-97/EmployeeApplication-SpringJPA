package com.myLearning.employeeApplication.dao;

import com.myLearning.employeeApplication.Entity.Employee;

import java.util.List;


public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int theID);
    void addEmployee(Employee theEmployee);
    void updateEmployee(Employee theEmployee);
    void deleteEmployeeByID(int id);
}
