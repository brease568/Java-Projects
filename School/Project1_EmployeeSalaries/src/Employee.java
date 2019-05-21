package com.brianrease;

public class Employee {
    private String name;
    private double monthlySalary;

    public Employee(String name, double monthlySalary) {
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    public double annualSalary() {
        double annualSalary = monthlySalary * 12;
        return annualSalary;
    }

    @Override
    public String toString() {
        return "\n\tName: " + name + "\n\tMonthly Salary: " + monthlySalary + "\n\tAnnual Salary: " + annualSalary();
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }
} //end of class Employee
