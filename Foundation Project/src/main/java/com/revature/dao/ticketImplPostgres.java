package com.revature.dao;

import com.revature.model.Employee;
import com.revature.model.Ticket;
import com.revature.util.connectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ticketImplPostgres implements ticketDAO{

    @Override
    public Ticket getByTicketId(int id) {
        return null;
    }


    @Override
    public List<Ticket> getAllTickets() {

        Connection conn = connectionUtil.getConnection();

        List<Ticket> tickets = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM ticket";

            ResultSet rs = stmt.executeQuery(sql);



            while (rs.next()){
                int id = rs.getInt("id");
                int amount = rs.getInt("amount");
                String status = rs.getString("status");
                int employid = rs.getInt("employee_id");

                Ticket tick = new Ticket(id, amount, status, employid);

                tickets.add(tick);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }


    @Override
    public List<Ticket> getTicketByEmployeeId(Employee employee) {

        List<Ticket> tickets = new ArrayList<>();

        try (Connection conn = connectionUtil.getConnection()){
            String sql = "SELECT * FROM ticket WHERE employee_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, employee.getId());

            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null){
                // if 'if' statement runs the query exist
                while (rs.next()){
                    int id = rs.getInt("id");
                    int amount = rs.getInt("amount");
                    String reason = rs.getString("reason");
                    int empid = rs.getInt("employee_id");
                    String status = rs.getString("status");

                    Ticket ticket = new Ticket(id, amount, reason, empid, status);
                    tickets.add(ticket);

                }
            }

        } catch(SQLException e){
            System.out.println("Username taken. Please try again.");
        }

        return tickets;
    }

    @Override
    public boolean createTicket(Ticket ticket, Employee employee) {
        try(Connection conn = connectionUtil.getConnection()) {
            String sql = "INSERT INTO ticket (amount, reason, employee_id, status) VALUES (?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, ticket.getAmount());
            stmt.setString(2, ticket.getStatus());
            stmt.setInt(3, employee.getId());
            stmt.setString(4, "Pending");


            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated == 1) {
                return true;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}