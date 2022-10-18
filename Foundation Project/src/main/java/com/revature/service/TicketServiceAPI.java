package com.revature.service;

import com.revature.dao.ticketImplPostgres;
import com.revature.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TicketServiceAPI extends HttpServlet{

    ticketImplPostgres td = new ticketImplPostgres();

    Employee employee = new Employee();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        td.getByTicketId(employee.getId());
        if (employee.getEmployeeid() != 0){
            System.out.println("Here are your current tickets");

        } else{
            System.out.println("No tickets to view at this time");
        }
    }

}
