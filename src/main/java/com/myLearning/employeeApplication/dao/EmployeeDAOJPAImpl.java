package com.myLearning.employeeApplication.dao;

import com.myLearning.employeeApplication.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAOJPAImpl implements EmployeeDAO {
    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJPAImpl(EntityManager theEnitityManager){
        this.entityManager = theEnitityManager;
    }


    @Override
    public List<Employee> findAll() {
       Query findAllQuery =  entityManager.createQuery("from Employee");
       return findAllQuery.getResultList();
    }

    @Override
    public Employee findById(int theID) {
        return entityManager.find(Employee.class, theID);
    }

    @Override
    public void addEmployee(Employee theEmployee) {
        entityManager.persist(theEmployee);
    }

    @Override
    public void updateEmployee(Employee theEmployee) {
        entityManager.merge(theEmployee);
    }

    @Override
    public void deleteEmployeeByID(int id) {
       Query deleteQuery = entityManager.createQuery("delete from Employee where id=:employeeId");
       deleteQuery.setParameter("employeeId", id);
       deleteQuery.executeUpdate();
    }
}
