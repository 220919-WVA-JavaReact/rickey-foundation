package com.revature;

import com.revature.model.Employee;
import com.revature.model.Ticket;
import com.revature.service.employeeService;
import com.revature.service.ticketService;
import com.revature.util.connectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        // single main method

        Employee employ = new Employee();

        employeeService es = new employeeService();

        ticketService ts = new ticketService();


        Employee LoggedEmploy = null;

        Ticket NewTicket = null;

        System.out.println("Please choose one of the following options:" + "\n" + "[1] to login" + "\n" + "[2] to register" + "\n" + "[3] Exit");//+ "\n" + "[4] to view all of your tickets tickets" + "\n" + "[5] to exit"); // prompts our users
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        boolean check = true;

        if (choice.equals("1")) {
            LoggedEmploy = es.login();

        } else if (choice.equals("2")) {
            LoggedEmploy = es.register();

        } else if (choice.equals("3")) {
            check = false;

        }
        if (LoggedEmploy != null) {
            System.out.println("[1] Create a new ticket \n[2] View your tickets \n[3] Exit");

            String subchoice = input.nextLine();

            switch (subchoice) {
                case "1":
                    ts.create(LoggedEmploy);
                    break;
                case "2":
                    ts.getAllTickets(LoggedEmploy);
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}