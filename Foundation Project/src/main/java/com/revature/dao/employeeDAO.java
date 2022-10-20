package com.revature.dao;

import com.revature.model.Employee;

import java.util.List;

public interface employeeDAO {


    Employee getByUsername(String username);

    Employee createEmployee(String fname, String lname, String email, String username, String password);

    List<Employee> getAllEmployees();


}

