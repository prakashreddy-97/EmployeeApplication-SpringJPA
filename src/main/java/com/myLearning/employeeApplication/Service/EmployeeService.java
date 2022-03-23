package com.myLearning.employeeApplication.Service;

import com.myLearning.employeeApplication.Entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int theID);
    void addEmployee(Employee theEmployee);
    void updateEmployee(Employee theEmployee);
    void deleteEmployeeByID(int id);
}
