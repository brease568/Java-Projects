/*
File: Student.java
Date: May 06, 2019
Author: Brian Rease
Purpose: This file is the Class for Student. It also contains the method called when updating a student record
and an overridden toString method.
 */

//package com.brianrease;

public class Student {
    private String name;
    private String major;
    //to avoid problems later - going to make both following variables double
    private double creditsCompleted; //divide by this
    private double qualityPoints; //this is (grades * credits) from courseCompleted()

    public Student(String name, String major) {
        this.name = name;
        this.major = major;
        this.creditsCompleted = 0;
        this.qualityPoints = 0.0;
    } //end of constructor

    public void courseCompleted(char grade, int creditHours) {
        creditsCompleted += creditHours;

        double x;
        if(Character.toUpperCase(grade) == 'A') {
            x = 4.0;
        } else if(Character.toUpperCase(grade) == 'B') {
            x = 3.0;
        } else if(Character.toUpperCase(grade) == 'C') {
            x = 2.0;
        } else if(Character.toUpperCase(grade) == 'D') {
            x = 1.0;
        } else {
            x = 0.0;
        }
        qualityPoints += (x * creditHours);
    } //end of courseCompleted()

    @Override
    public String toString() {
        //Don't really like the way the formatting is working for String.format - come back if needed
        //String myString = String.format("Name: " + "%15s \nMajor: %15s \nGPA: %17.2f", name, major, qualityPoints);
        if(creditsCompleted == 0) {
            return "Name: " + name + "\nMajor: " + major + "\nGPA: " + 4.0;
            //return myString;
        } else {
            return "Name: " + name + "\nMajor: " + major + "\nGPA: " + (qualityPoints / creditsCompleted);
        }
    }
} //end of Student
