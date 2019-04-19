/*
File: Account.java
Date: Apr 13, 2019
Author: Brian Rease
Purpose: This file provides the class to create an Account object. It also provides methods for the
functionality of all of the buttons on the ATM GUI.
 */

//package com.brianrease;

import javax.swing.*;

public class Account {

    private double funds;
    private static int withdrawCounter = 0; //create a static variable to be common to all objects
    private static final double SERVICE_CHARGE = 1.50;

    //constructor for Account object - will supply amount of funds for each account
    public Account(double funds) {
        this.funds = funds;
    }

    public double getFunds() {
        return funds;
    } //end of getFunds()

    public void depositFunds(double amount) {
        funds += amount;
    } //end of depositFunds()

    public void transferFunds(double transferAmount) {
        funds -= transferAmount;
        JOptionPane.showMessageDialog(null, "$" + transferAmount + " transferred.");
    } //end of transferFunds()

    public void withdrawFunds(double withdrawalAmount) throws InsufficientFunds {
        withdrawCounter++;
        if(withdrawalAmount > funds) {
            throw new InsufficientFunds();
        } else if(withdrawCounter > 4) {
            if (withdrawalAmount + SERVICE_CHARGE > funds) {
                JOptionPane.showMessageDialog(null, "You have withdrawn more than four times. " +
                        "You are charged $" + SERVICE_CHARGE + " service charge for each new withdrawal.");
                throw new InsufficientFunds();
            } else {
                funds = funds - withdrawalAmount - SERVICE_CHARGE;
                JOptionPane.showMessageDialog(null, "You have withdrawn more than 4 times. You have been charged a service fee of $"
                        + SERVICE_CHARGE + ".\n$" + withdrawalAmount + " withdrawn successfully.");
            }
        }
        else {
            funds -= withdrawalAmount;
            JOptionPane.showMessageDialog(null, "$" + withdrawalAmount + " withdrawn.");
        }
    } //end of withdrawFunds()
} //end of Account
