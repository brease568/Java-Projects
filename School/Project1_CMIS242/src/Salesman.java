package com.brianrease;

public class Salesman extends Employee {
    private int annualSales;

    public Salesman(String name, double monthlySalary, int annualSales) {
        super(name, monthlySalary);
        this.annualSales = annualSales;
    }

    @Override
    public double annualSalary() {
        double baseSalary = super.annualSalary();
        double commission = 0.02 * annualSales;

        if (commission > 20000) {
            baseSalary += 20000;
        } else {
            baseSalary += commission;
        }
        return baseSalary;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tAnnual Sales: " + annualSales;
    }
}
