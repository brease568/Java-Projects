/*
File: MachineGUI.java
Date: Apr 13, 2019
Author: Brian Rease
Purpose: A program that implements an ATM machine with functionality for withdrawing, transferring,
and depositing funds as well as checking a balance. This file implements the ATM GUI and
creates a checking and savings account.
 */

//package com.brianrease;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MachineGUI extends JFrame {

    //create all of the objects needed for the GUI
    private JButton withdrawButton = new JButton("Withdraw"),
        depositButton = new JButton("Deposit"),
        transferButton = new JButton("Transfer to"),
        balanceButton = new JButton("Balance");
    private JRadioButton checkingRadio = new JRadioButton("Checking"),
        savingRadio = new JRadioButton("Savings");
    private JTextField outputField = new JTextField(20);

    //create 2 account objects
    Account checkingAccount = new Account(50);
    Account savingsAccount = new Account(50);

    //create the constructor for the GUI
    public MachineGUI() {
        super("ATM Machine"); //title
        setLocationRelativeTo(null); //set to launch in middle of the screen
        setSize(375,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,2,10,10));
        buttonPanel.add(withdrawButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(transferButton);
        buttonPanel.add(balanceButton);
        add(buttonPanel);
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(1,2,10,10));
        radioPanel.add(checkingRadio);
        radioPanel.add(savingRadio);
        add(radioPanel);
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(checkingRadio);
        radioGroup.add(savingRadio);
        checkingRadio.setSelected(true); //set default radio button to be selected
        add(outputField);
        outputField.setEditable(true);

        //create all of the event handlers below - for the 4 buttons on the GUI
        balanceButton.addActionListener(event -> {
            double tmp; //easy variable to hold value for the funds
            if(checkingRadio.isSelected()) {
                tmp = checkingAccount.getFunds();
            } else {
                tmp = savingsAccount.getFunds();
            }
            JOptionPane.showMessageDialog(null, "$" + tmp);
        }); //end of balance button action listener

        depositButton.addActionListener(event -> {
            try {
                double deposit = Double.parseDouble(outputField.getText());
                if(checkingRadio.isSelected()) {
                    checkingAccount.depositFunds(deposit);
                } else {
                    savingsAccount.depositFunds(deposit);
                }
                JOptionPane.showMessageDialog(null, "Deposited $" + deposit);
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "You must enter a number!");
            }
        }); //end of deposit button action listener

        transferButton.addActionListener(event -> {
            try {
                double transfer = Double.parseDouble(outputField.getText());
                if(checkingRadio.isSelected()) {
                    if(transfer > savingsAccount.getFunds()) {
                        throw new InsufficientFunds();
                    } else {
                        savingsAccount.transferFunds(transfer);
                        checkingAccount.depositFunds(transfer);
                    }
                } else {
                    if(transfer > checkingAccount.getFunds()) {
                        throw new InsufficientFunds();
                    } else {
                        checkingAccount.transferFunds(transfer);
                        savingsAccount.depositFunds(transfer);
                    }
                }
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "You must enter a number!");
            } catch(InsufficientFunds e) {
                //JOptionPane.showMessageDialog(null, "Insufficient Funds!");
            }
        }); //end of transferButton action listener

        withdrawButton.addActionListener(event -> {
            try {
                double withdrawal = Double.parseDouble(outputField.getText());
                if(withdrawal % 20 != 0) {
                    JOptionPane.showMessageDialog(null, "Withdrawal amount must be in increments of $20");
                } else {
                    if(checkingRadio.isSelected()) {
                        checkingAccount.withdrawFunds(withdrawal);
                    } else {
                        savingsAccount.withdrawFunds(withdrawal);
                    }
                }
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "You must enter a number!");
            } catch (InsufficientFunds e) {
                //JOptionPane.showMessageDialog(null, "Insufficient Funds!");
            }
        }); //end of withdrawButton action listener

    } //end of MachineGUI constructor

    public static void main(String[] args) {
        //create the window and set to visible
        MachineGUI window = new MachineGUI();
        window.setVisible(true);

    } //end of main()
} //end of Main
