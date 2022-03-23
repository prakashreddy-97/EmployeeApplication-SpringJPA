package com.myLearning.employeeApplication.dao;

import com.myLearning.employeeApplication.Entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO{

    //Create an entity manager Object
    private EntityManager entityManager;

    //Inject EntityManager
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
       //Create a Hibernate Session
        Session currentSession = entityManager.unwrap(Session.class);

        //Create a Query
        Query<Employee> employeeQuery = currentSession.createQuery("from Employee", Employee.class);

        //Execute the query
        List<Employee> employees = employeeQuery.getResultList();

        //return result
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Employee employee = currentSession.get(Employee.class,theId);
        return employee;
    }

    @Override
    public void addEmployee(Employee theEmployee) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theEmployee);
//        currentSession.save(theEmployee);
    }

    @Override
    public void updateEmployee(Employee theEmployee) {
        Session currentSession = entityManager.unwrap(Session.class);
//        currentSession.saveOrUpdate(theEmployee);
        currentSession.update(theEmployee);
    }

    @Override
    public void deleteEmployeeByID(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Employee> deleteEmployee= currentSession.createQuery("delete from Employee where id=:employeeId");
//        Query<Employee> deleteEmployee= currentSession.dele("delete from Employee where id=:employeeId",Employee.class);
        deleteEmployee.setParameter("employeeId", theId);
        deleteEmployee.executeUpdate();
    }

}
