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
        Ticket ticket = new Ticket();

        try (Connection conn = connectionUtil.getConnection()){
            String sql = "SELECT * FROM ticket WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null){
                // if 'if' statement runs the query exist
                if (rs.next()){
                    int tickid = rs.getInt("id");
                    int amount = rs.getInt("amount");
                    String reason = rs.getString("reason");
                    int empid = rs.getInt("employee_id");
                    String status = rs.getString("status");

                    ticket = new Ticket(tickid, amount, reason, empid, status);


                }
            }

        } catch(SQLException e){
            System.out.println("Username taken. Please try again.");
        }

        return ticket;
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

    public List<Ticket> getTicketByStatus(Employee employee) {

        List<Ticket> tickets = new ArrayList<>();

        try (Connection conn = connectionUtil.getConnection()){
            String sql = "SELECT * FROM ticket WHERE employee_id = ? AND status = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, employee.getId());
            stmt.setString(2, "Pending");

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

    // @Override
    public Ticket createTicket(Ticket ticket, Employee employee) {
        Ticket newTicket = new Ticket();
        try(Connection conn = connectionUtil.getConnection()) {
            String sql = "INSERT INTO ticket (amount, reason, employee_id, status) VALUES (?,?,?,?) RETURNING *";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, ticket.getAmount());
            stmt.setString(2, ticket.getReason());
            stmt.setInt(3, employee.getId());
            stmt.setString(4, "Pending");
            ResultSet rs;
            if((rs = stmt.executeQuery()) != null){
                rs.next();
                int id = rs.getInt("id");
                int amount = rs.getInt("amount");
                String reason = rs.getString("reason");
                int employId = rs.getInt("employee_id");
                String status = rs.getString("status");

                newTicket = new Ticket(id, amount, reason, employId, status);


            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return newTicket;
    }
    public List<Ticket> viewAllByStatus(String status){
        List<Ticket> tickets = new ArrayList<>();
        try(Connection conn = connectionUtil.getConnection()) {
            String sql = "SELECT * FROM ticket WHERE status = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, status);
            ResultSet rs;
            if((rs = stmt.executeQuery()) != null){
                while(rs.next()) {
                    int id = rs.getInt("id");
                    int amount = rs.getInt("amount");
                    String reason = rs.getString("reason");
                    int employId = rs.getInt("employee_id");
                    String status1 = rs.getString("status");

                    Ticket ticket = new Ticket(id, amount, reason, employId, status1);
                    tickets.add(ticket);
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return tickets;

    }

    @Override
    public Ticket updateTicket(int id, String status) {
        Ticket ticket = new Ticket();
        try(Connection conn = connectionUtil.getConnection()) {
            String sql = "UPDATE ticket SET status = ? WHERE id = ? RETURNING *";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, status);
            stmt.setInt(2, id);
            ResultSet rs;
            if((rs = stmt.executeQuery()) != null){
                if(rs.next()) {
                    int ticketId = rs.getInt("id");
                    int amount = rs.getInt("amount");
                    String reason = rs.getString("reason");
                    int employId = rs.getInt("employee_id");
                    String status1 = rs.getString("status");

                    ticket = new Ticket(ticketId, amount, reason, employId, status1);
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return ticket;

    }
}