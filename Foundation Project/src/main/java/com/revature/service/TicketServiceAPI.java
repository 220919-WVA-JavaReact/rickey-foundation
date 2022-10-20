package com.revature.service;

import com.revature.dao.ticketDAO;
import com.revature.dao.ticketImplPostgres;
import com.revature.model.Employee;
import com.revature.model.Ticket;


public class TicketServiceAPI{
    
    ticketDAO td = new ticketImplPostgres();


    public Ticket create(int amount, String reason, Employee employee) {
        Ticket ticket = new Ticket();
        // Ticket ticket = td.getByTicketId(amount);
        ticket.setAmount(amount);
        ticket.setReason(reason);
//        if(reason.equals(null)){
//            System.out.println("Ticket must have a reason/description");
//
//        }

        return td.createTicket(ticket, employee);
    }
}
