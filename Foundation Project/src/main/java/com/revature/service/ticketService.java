package com.revature.service;

import com.revature.dao.ticketImplPostgres;
import com.revature.model.Employee;

import java.util.List;
import java.util.Scanner;
import com.revature.dao.ticketDAO;
import com.revature.model.Ticket;

public class ticketService {

    Scanner sc = new Scanner(System.in);

    ticketDAO td = new ticketImplPostgres();

    public void create(Employee employee){
        Ticket ticket = null;

        System.out.println("Please enter the amount for your ticket reimbursement request:");
        String a = sc.nextLine();
        int amount = Integer.parseInt(a);
        System.out.println("Please enter the reason for your reimbursement request:");
        String reason = sc.nextLine();


        ticket = new Ticket(amount, reason, employee.getEmployeeid());


        //boolean successful = td.createTicket(ticket, employee);


//        if(successful){
//            System.out.println("Your ticket has been submitted!");
//        } else {
//            System.out.println("Invalid ticket submission");
//        }
    }


    public Employee getAllTickets(Employee employee) {

        List<Ticket> tickets = td.getTicketByEmployeeId(employee);

        System.out.println(tickets);

        return employee;
    }
}
