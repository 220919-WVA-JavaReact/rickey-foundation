package com.revature.model;

import java.util.Objects;

public class Ticket {

    private int id;
    private int amount;
    private String reason;

    private int employee_id;

    private String status;
    private Employee employee;

    public Ticket() {
    }

    public Ticket(int id, int amount, String reason, int employee_id, String status) {
        this.id = id;
        this.amount = amount;
        this.reason = reason;
        this.employee_id = employee_id;
        this.status = status;
    }


    public Ticket(int id, int amount, String reason) {
        this.id = id;
        this.amount = amount;
        this.reason = reason;
    }

    public Ticket(int amount, String reason, int employee_id) {
        this.amount = amount;
        this.reason = reason;
        this.employee_id = employee_id;
    }

    public Ticket(int id, int amount, String reason, int employee_id) {
        this.id = id;
        this.amount = amount;
        this.reason = reason;
        this.employee_id = employee_id;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && amount == ticket.amount && reason.equals(ticket.reason) && employee.equals(ticket.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, reason, employee_id, status, employee);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", amount=" + amount +
                ", reason='" + reason + '\'' +
                ", employee_id=" + employee_id +
                ", status='" + status + '\'' +
                ", employee=" + employee +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Employee getEmployee(Employee loggedEmploy) {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
