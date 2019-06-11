/*
File: Main.java
Date: Mar 27, 2019
Author: Brian Rease
Purpose: Write a program that computes the salaries for a collection of -
employees of different types.
 */



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException{

        //Create a scanner to read the file 'Employees.txt'
        Scanner testScanner = new Scanner(new File("Employees.txt"));

        Employee[] my2014Array = new Employee[20];
        int i = 0;

        Employee[] my2015Array = new Employee[20];
        int j = 0;

        //while loop to read file and put information into an array:
        //hasNextLine() - true if another line - does not advance past input
        while(testScanner.hasNextLine()) {
            //nextLine() - advances scanner past current line and returns input skipped
            String myLine = testScanner.nextLine();
            //creates String array and splits myLine based on a single white space
            String[] myToken = myLine.split(" ");

            if(myToken[0].equals("2014")) {
                if(myToken[1].equals("Employee")) {
                    my2014Array[i] = new Employee(myToken[2], Integer.parseInt(myToken[3]));
                    i++;
                } else if(myToken[1].equals("Salesman")) {
                    my2014Array[i] = new Salesman(myToken[2], Integer.parseInt(myToken[3]), Integer.parseInt(myToken[4]));
                    i++;
                } else if(myToken[1].equals("Executive")) {
                    my2014Array[i] = new Executive(myToken[2], Integer.parseInt(myToken[3]), Integer.parseInt(myToken[4]));
                    i++;
                } else {
                    System.out.println("Error: 2014");
                } //end 2014 if statement
            //start 2015 if statement
            } else if(myToken[0].equals("2015")) {
                if(myToken[1].equals("Employee")) {
                    my2015Array[j] = new Employee(myToken[2], Integer.parseInt(myToken[3]));
                    j++;
                } else if(myToken[1].equals("Salesman")) {
                    my2015Array[j] = new Salesman(myToken[2], Integer.parseInt(myToken[3]), Integer.parseInt(myToken[4]));
                    j++;
                } else if(myToken[1].equals("Executive")) {
                    my2015Array[j] = new Executive(myToken[2], Integer.parseInt(myToken[3]), Integer.parseInt(myToken[4]));
                    j++;
                } else {
                    System.out.println("Error: 2015");
                }
            } //end of if statement for 2015 array
        } //end of while statement for creating employee objects

        System.out.println("** All 2014 Employees **");
        for(int x = 0; x < 20; x++) {
            if(my2014Array[x] == null) {
                //have to implement this here because I don't know how to handle array being empty gracefully with a 'while'
                System.out.println("\nThere are no more 2014 employees.");
                break;
            } else {
                System.out.println(my2014Array[x]);
            }
        }

        System.out.println("\n** All 2015 Employees **");
        for(int z = 0; z < 20; z++) {
            if(my2015Array[z] == null) {
                //have to implement this here because I don't know how to handle array being empty gracefully with a 'while'
                System.out.println("\nThere are no more 2015 employees.");
                break;
            } else {
                System.out.println(my2015Array[z]);
            }
        }


        //implement code for computing annual salary for each year
        double average_2014;
        double sum_2014 = 0;

        double average_2015 = 0;
        double sum_2015 = 0;

        for(int x = 0; x < i; x++) {
            if (my2014Array[x] instanceof Salesman) {
                Salesman t;
                t = (Salesman)my2014Array[x];
                //System.out.println(t.annualSalary());
                sum_2014 = sum_2014 + t.annualSalary();
            }
            else if (my2014Array[x] instanceof Employee) {
                Employee t;
                t = (Employee)my2014Array[x];
                //System.out.println(t.annualSalary());
                sum_2014 = sum_2014 + t.annualSalary();
            }
            else if (my2014Array[x] instanceof Executive) {
                Executive t;
                t = (Executive)my2014Array[x];
                sum_2014 = sum_2014 + t.annualSalary();
            }
        } //end of for loop for 2014 annual salary average

        for(int x = 0; x < j; x++) {
            if (my2015Array[x] instanceof Salesman) {
                Salesman t;
                t = (Salesman)my2015Array[x];
                //System.out.println(t.annualSalary());
                sum_2015 = sum_2015 + t.annualSalary();
            }
            else if (my2015Array[x] instanceof Employee) {
                Employee t;
                t = (Employee)my2015Array[x];
                //System.out.println(t.annualSalary());
                sum_2015 = sum_2015 + t.annualSalary();
            }
            else if (my2015Array[x] instanceof Executive) {
                Executive t;
                t = (Executive)my2015Array[x];
                sum_2015 = sum_2015 + t.annualSalary();
            }
        } //end of for loop for 2015 annual salary average

        average_2014 = sum_2014 / i;
        //using isNaN() method to handle having no employees
        if(Double.isNaN(average_2014)) {
            System.out.println("\nAverage annual salary of 2014 employees: $0");
        } else {
            System.out.printf("\nAverage annual salary of 2014 employees: $" + "%.2f", average_2014);
        }

        average_2015 = sum_2015 / j;
        //using isNaN() method to handle having no employees
        if(Double.isNaN(average_2015)) {
            System.out.println("\nAverage annual salary of 2015 employees: $0");
        } else {
            System.out.printf("\nAverage annual salary of 2015 employees: $" + "%.2f", average_2015);
        }
        System.out.println();

    } //end of main()
} //end of class Main
