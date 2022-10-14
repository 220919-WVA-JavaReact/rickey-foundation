package com.revature.dao;
import com.revature.model.Ticket;
import com.revature.model.Employee;
import java.util.List;

public interface ticketDAO {
    Ticket getByTicketId(int id);

    List<Ticket> getAllTickets();


    List<Ticket> getTicketByEmployeeId(Employee id);




    boolean createTicket(Ticket ticket, Employee employee);
}
