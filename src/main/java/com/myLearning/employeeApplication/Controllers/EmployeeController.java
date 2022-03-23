package com.myLearning.employeeApplication.Controllers;

import com.myLearning.employeeApplication.Entity.Employee;
import com.myLearning.employeeApplication.Service.EmployeeService;
import com.myLearning.employeeApplication.dao.EmployeeDAO;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.HttpResource;

import java.util.List;

@RestController
//@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;

    //Inject the Employee DAO class
    @Autowired
    public EmployeeController(EmployeeService theEmployeeService){
        this.employeeService = theEmployeeService;
    }

    //Create a REST endpoint
    @GetMapping("/employees")
    public List<Employee> findAllEmployees(){
        return employeeService.findAll();
    }

    @GetMapping("/employee")
    public Employee findEmployeeById(@RequestParam int employeeID){
        return employeeService.findById(employeeID);
    }

    @PostMapping("/addEmployee")
    public HttpStatus addEmployee(@RequestBody Employee employee){
        employee.setId(0);
        employeeService.addEmployee(employee);

        return HttpStatus.OK;
    }

    @PutMapping("/updateEmployee")
    public HttpStatus updateEmployee(@RequestBody Employee employee){
        Employee updatedEmployee;
        try {
            employeeService.updateEmployee(employee);
            updatedEmployee = employeeService.findById(employee.getId());

            if (updatedEmployee.equals(employee)){
                return HttpStatus.resolve(200);
            }else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Unknown error occured cannot update the employee" );
            }
        } catch (ResponseStatusException e) {
            e.getMessage();
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @DeleteMapping("/deleteEmployee")
    public HttpStatus deleteEmployee(@RequestParam int id){
        employeeService.deleteEmployeeByID(id);

        return HttpStatus.resolve(200);
    }
}
