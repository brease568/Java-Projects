/*
File: Sequence.java
Date: Apr 24, 2019
Author: Brian Rease
Purpose: A utility class to perform computations for the application.
 */

//package com.brianrease;

public final class Sequence {

    private static int efficiency = 0;

    //Private constructor to prevent instantiation
    //got the idea of this code for the constructor online for reasons the article explains for implementation:
    //https://www.vojtechruzicka.com/avoid-utility-classes/
    private Sequence() {
        throw new UnsupportedOperationException();
    }

    public static int computeIterative(int value) {
        efficiency = 0;
        int myResult = 0;
        if(value == 0) {
            myResult = 0;
        } else if(value == 1) {
            myResult = 1;
        } else {
            int lastTerm = 1;
            int secondLastTerm = 0;
            for(int i = 2; i <= value; i++) {
                efficiency++; //increment here to fulfill number of iterations of the loop
                myResult = (2 * lastTerm) + secondLastTerm;
                secondLastTerm = lastTerm;
                lastTerm = myResult;
            } //end of for loop
        } //end of else
        return myResult;
    } //end of computeIterative()

    //this is the helper method - initializes the efficiency counter to 0
    public static int computeRecursive(int value2) {
        efficiency = 0;
        return actualRecursive(value2);
    }

    //this is the actual recursive method that does the computation
    //called via above method computeRecursive()
    private static int actualRecursive(int actualValue2) {
        efficiency++; //increment here so that each call to 'actualRecursive()' is counted
        int myResult2 = 0;
        if(actualValue2 == 0) {
            myResult2 = 0;
        } else if(actualValue2 == 1) {
            myResult2 = 1;
        } else {
            myResult2 = (2 * actualRecursive(actualValue2 - 1)) + actualRecursive(actualValue2 - 2);
        } //end of else
        return myResult2;
    } //end of computeRecursive()

    //simple method to return the left behind values of the efficiency variable
    public static int getEfficiency() {
        return efficiency;
    }
}
