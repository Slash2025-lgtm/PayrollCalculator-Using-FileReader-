package com.pluralsight;

public class Employee {

    private int employeeId;
    private String name;
    private double hoursWorked;
    private double payRate;

    public Employee() {
        this.employeeId = 0;
        this.name = "";
        this.hoursWorked = 0.0;
        this.payRate = 0.0;
    }

    public double getGrossPay() {
        return (hoursWorked * payRate);
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }
}
