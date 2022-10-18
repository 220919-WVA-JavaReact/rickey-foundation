package com.revature.service;

import com.revature.dao.EmployeeDAOImplPostgres;
import com.revature.model.Employee;
import com.revature.service.employeeService;

public class EmployeeServiceAPI {
    EmployeeDAOImplPostgres ed = new EmployeeDAOImplPostgres();

    employeeService es = new employeeService();

    public String login(String uname, String pword){
        Employee employee = new Employee();
        if(employee.getUsername().equals(uname) && employee.getPassword().equals(pword)){
            System.out.println("You have been logged in!");
        } else{
            System.out.println("Invalid login");
            return null;
        }
        return uname;
    }

    public String register(String fname, String lname, String uname, String pword, String email){
        Employee employee = new Employee();
        employee = ed.createEmployee(fname, lname, uname, pword, email);
        if(employee.getEmployeeid() != 0){
            System.out.println("You have been registered!");
        }
        return uname;

    }

}
