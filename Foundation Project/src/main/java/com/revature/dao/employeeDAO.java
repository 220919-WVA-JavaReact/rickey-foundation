package com.revature.dao;

import com.revature.model.Employee;

import java.util.List;

public interface employeeDAO {
    // providing the basic requirements we want our DAO to have
    // essentially a list of methods that will be implemented for a specific kind of db

    Employee getByUsername(String username);

    Employee createEmployee(String fname, String lname, String email, String username, String password);

    List<Employee> getAllEmployees();


}

