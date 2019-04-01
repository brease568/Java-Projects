package com.brianrease;

public class Executive extends Employee {
    private double stockPrice;

    public Executive(String name, double monthlySalary, double stockPrice) {
        super(name, monthlySalary);
        this.stockPrice = stockPrice;
    }

    @Override
    public double annualSalary() {
        double baseSalary = super.annualSalary();

        if(stockPrice > 50) {
            baseSalary += 30000;
        }
        return baseSalary;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tStock Price: $" + stockPrice;
    }
}
