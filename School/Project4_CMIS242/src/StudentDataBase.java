/*
File: StudentDataBase.java
Date: May 06, 2019
Author: Brian Rease
Purpose: This program implements a student database. The database consists of names, grades, etc.
This file implements the GUI to the database and implements the database interactions.
 */

//package com.brianrease;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class StudentDataBase extends JFrame {

    //create all of the objects needed for the GUI
    private JButton processButton = new JButton("Process Request");
    private JTextField idField = new JTextField(20),
        nameField = new JTextField(20),
        majorField = new JTextField(20);
    private JLabel idLabel = new JLabel("Id:"),
        nameLabel = new JLabel("Name:"),
        majorLabel = new JLabel("Major:"),
        chooseLabel = new JLabel("Choose Selection:");

    //create the combo box:
    String[] myOptions = {"Insert", "Delete", "Find", "Update"};
    private JComboBox<String> myDropDown = new JComboBox<>(myOptions);

    //create a hashMap to implement the database
    HashMap<Integer, Student> studentHashMap = new HashMap<>();

    //create constructor for the GUI
    public StudentDataBase() {
        super("Project 4"); //title
        setLocationRelativeTo(null);

        //set close operation - may need to change later??
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(0,2,10,10));
        myPanel.add(idLabel);
        myPanel.add(idField);
        myPanel.add(nameLabel);
        myPanel.add(nameField);
        myPanel.add(majorLabel);
        myPanel.add(majorField);
        myPanel.add(chooseLabel);
        myPanel.add(myDropDown);
        myPanel.add(processButton);

        //add the panel with everything on it (myPanel) and pack to size
        add(myPanel);
        pack();

        //create the ActionEventListener for the button
        processButton.addActionListener(event -> {
            try {
                if(myDropDown.getSelectedItem() == "Insert") {
                    databaseInsert(nameField.getText());
                } else if(myDropDown.getSelectedItem() == "Find") {
                    databaseFind(Integer.parseInt(idField.getText()));
                } else if(myDropDown.getSelectedItem() == "Delete") {
                    databaseDelete(Integer.parseInt(idField.getText()));
                } else if(myDropDown.getSelectedItem() == "Update") {
                    databaseUpdate(Integer.parseInt(idField.getText()));
                }
            } catch(NumberFormatException eF) {
                if(idField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "You must enter an ID!");
                } else {
                    JOptionPane.showMessageDialog(null, "The ID must be a number!");
                }
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, "Something has gone wrong.");
            } //end of catch()
        }); //end of actionListener()


    } //end of constructor

    //create methods for the database
    public void databaseInsert(String studentName) {
        int studentID = Integer.parseInt(idField.getText());
        String studentMajor = majorField.getText();
        if(studentHashMap.containsKey(studentID)) {
            JOptionPane.showMessageDialog(null, "The student ID entered already exists.");
        } else if((nameField.getText().equals("")) || majorField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "You must enter a name and major!");
        } else {
            //the code below allows to not have to name bind a Student object to a variable - referring to a specific student is handled in databaseUpdate()
            studentHashMap.put(studentID, new Student(studentName, studentMajor));
            JOptionPane.showMessageDialog(null, "Student inserted into the database successfully.");
        }
    } //end of databaseInsert()

    public void databaseFind(int findKey) {
        if(!studentHashMap.containsKey(findKey)) {
            JOptionPane.showMessageDialog(null, "The student ID does not exist.");
        } else {
            JOptionPane.showMessageDialog(null, "Successful find!\n" + "ID: " + findKey + "\n" + studentHashMap.get(findKey));
        }
    } //end of databaseFind()

    public void databaseDelete(int deleteKey) {
        if(!studentHashMap.containsKey(deleteKey)) {
            JOptionPane.showMessageDialog(null, "The student ID does not exist.");
        } else {
            studentHashMap.remove(deleteKey);
            JOptionPane.showMessageDialog(null, "Student ID deleted");
        }
    } //end of databaseDelete()

    public void databaseUpdate(int updateKey) {
        if(!studentHashMap.containsKey(updateKey)) {
            JOptionPane.showMessageDialog(null, "The student ID does not exist.");
        } else {
            //this is the implementation to get two JOptionPane windows to pop up asking for input
            //this implementation comes from examples on https://docs.oracle.com/javase/8/docs/api/javax/swing/JOptionPane.html
            Object[] possibleGrades = {'A', 'B', 'C', 'D', 'F'};
            Object selectedGrade = JOptionPane.showInputDialog(null, "Choose grade", "", JOptionPane.INFORMATION_MESSAGE, null, possibleGrades, possibleGrades[0]);
            Object[] possibleCredits = {3, 6};
            Object selectedCredits = JOptionPane.showInputDialog(null, "Choose credits", "", JOptionPane.INFORMATION_MESSAGE, null, possibleCredits, possibleCredits[0]);

            Student currentStudent = studentHashMap.get(updateKey);
            //I have to cast the parameters here because the JOptionPane implementation above is for type Object and the method being called needs char and int
            currentStudent.courseCompleted((char)selectedGrade, (int)selectedCredits);
            JOptionPane.showMessageDialog(null, "Student record updated successfully.");
        }
    }//end of databaseUpdate()

    public static void main(String[] args) {
        //create GUI window and set to visible
        StudentDataBase window = new StudentDataBase();
        window.setVisible(true);
    } //end of main()
} //end of StudentDataBase
