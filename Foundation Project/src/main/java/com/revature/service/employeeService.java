package com.revature.service;

import com.revature.dao.EmployeeDAOImplPostgres;
import com.revature.dao.employeeDAO;
import com.revature.model.Employee;

import java.util.List;
import java.util.Scanner;

public class employeeService {

    employeeDAO ed = new EmployeeDAOImplPostgres();

    Scanner sc = new Scanner(System.in);

    public Employee login(){
        System.out.println("Are you a manager?" + "\n" + "[1] Yes" + "\n" + "[2] No");
        String ans = sc.nextLine();
        if(ans.equals("1")){
            System.out.println("Please enter your username:");
            String username = sc.nextLine();
            System.out.println("Please enter your password:");
            String password = sc.nextLine();

            Employee manager = ed.getByUsername(username);

            if(manager.getUsername().equals(username)){
                System.out.println("You have been logged in!");

            } else {
                System.out.println("INVALID LOGIN");

            }
            return manager;

        }if (ans.equals("2")){
            System.out.println("Please enter your username:");
            String usernameemploy = sc.nextLine();
            System.out.println("Please enter your password:");
            String passwordemploy = sc.nextLine();

            Employee employ = ed.getByUsername(usernameemploy); // calling database to grab info based off username

            if(employ.getUsername().equals(usernameemploy)){
                System.out.println("You have been logged in!");

            }

            else {
                System.out.println("INVALID LOGIN");

            }
            return employ;

        }




        return null;
    }

    public Employee register(){
        System.out.println("Please enter your First Name:");
        String fname = sc.nextLine();
        System.out.println("Please enter your Last Name:");
        String lname = sc.nextLine();
        System.out.println("Please enter your username:");
        String username = sc.nextLine();
        System.out.println("Please enter your password:");
        String password = sc.nextLine();
        System.out.println("Please enter your email:");
        String email = sc.nextLine();

        Employee employee = ed.createEmployee(fname, lname, username, password, email);

        System.out.println("You have been added to the database!");

        return employee;
    }

    // this is to view all employees

    public void getAllEmployees(){
        System.out.println("Using the database to return all employee objects");

        List<Employee> employeeList = ed.getAllEmployees();

        for(Employee employee: employeeList){ // printing each value of the list
            System.out.println(employee);
        }
    }

}
