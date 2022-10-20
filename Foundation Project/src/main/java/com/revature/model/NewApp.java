package com.revature.model;

import java.util.Objects;

public class NewApp {
    private int id;

    private String fname;

    private String lname;

    private String email;

    private String uname;

    private String pword;

    public NewApp() {
    }

    public NewApp(String uname, String pword) {
        this.uname = uname;
        this.pword = pword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPword() {
        return pword;
    }

    public void setPword(String pword) {
        this.pword = pword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewApp newApp = (NewApp) o;
        return id == newApp.id && Objects.equals(fname, newApp.fname) && Objects.equals(lname, newApp.lname) && Objects.equals(email, newApp.email) && Objects.equals(uname, newApp.uname) && Objects.equals(pword, newApp.pword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fname, lname, email, uname, pword);
    }

    @Override
    public String toString() {
        return "NewApp{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", uname='" + uname + '\'' +
                ", pword='" + pword + '\'' +
                '}';
    }
}
