/*
File: InsufficientFunds.java
Date: Apr 13, 2019
Author: Brian Rease
Purpose: This file implements a user defined exception.
 */

//package com.brianrease;

import javax.swing.*;

public class InsufficientFunds extends Exception {

    public InsufficientFunds() {
        JOptionPane.showMessageDialog(null, "Insufficient Funds!");
    }
} //end of InsufficientFunds
