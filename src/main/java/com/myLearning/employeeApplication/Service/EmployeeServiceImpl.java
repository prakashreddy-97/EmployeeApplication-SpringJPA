package com.myLearning.employeeApplication.Service;

import com.myLearning.employeeApplication.Entity.Employee;
import com.myLearning.employeeApplication.dao.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(@Qualifier("employeeDAOJPAImpl") EmployeeDAO theEmployeeDAO){
        this.employeeDAO = theEmployeeDAO;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
      return this.employeeDAO.findAll();
    }

    @Override
    @Transactional
    public Employee findById(int theID) {
        return employeeDAO.findById(theID);
    }

    @Override
    @Transactional
    public void addEmployee(Employee theEmployee) {
        employeeDAO.addEmployee(theEmployee);
    }

    @Override
    @Transactional
    public void updateEmployee(Employee theEmployee) {
        employeeDAO.updateEmployee(theEmployee);
    }

    @Override
    @Transactional
    public void deleteEmployeeByID(int id) {
        employeeDAO.deleteEmployeeByID(id);
    }
}
