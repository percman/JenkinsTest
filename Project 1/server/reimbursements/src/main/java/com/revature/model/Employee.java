package com.revature.model;

import com.revature.util.Mappable;

public class Employee implements Mappable {
    private String username;
    private String password;
    private int eid;
    private String firstName;
    private String lastName;
    private String address;
    private boolean isManager;

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee(String username, int eid, String firstName, String lastName, String address, boolean isManager) {

        this.username = username;
        this.eid = eid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.isManager = isManager;
    }

    public Employee () {}

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", eid=" + eid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", isManager=" + isManager +
                '}';
    }
}