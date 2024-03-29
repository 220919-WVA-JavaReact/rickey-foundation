package com.revature.dao;
import com.revature.model.Ticket;
import com.revature.model.Employee;
import java.util.List;

public interface ticketDAO {
    Ticket getByTicketId(int id);

    List<Ticket> getAllTickets();


    List<Ticket> getTicketByEmployeeId(Employee id);




    Ticket createTicket(Ticket ticket, Employee employee);

    List<Ticket> viewAllByStatus(String status);


    Ticket updateTicket(int id, String status);
}
