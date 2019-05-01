/*
File: ProgramGUI.java
Date: Apr 24, 2019
Author: Brian Rease
Purpose: A program that calculates the terms of the following sequence of
numbers: 0 1 2 5 12 29....
This file implements the program GUI and object creation.
 */

//package com.brianrease;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ProgramGUI extends JFrame {

    //create all of the objects needed for the GUI
    private JButton computeButton = new JButton("Compute");
    private JRadioButton iterativeRadio = new JRadioButton("Iterative"),
        recursiveRadio = new JRadioButton("Recursive");
    private JTextField numField = new JTextField(20),
        resultField = new JTextField(20),
        efficiencyField = new JTextField(20);

    //create constructor for the GUI
    public ProgramGUI() {
        super("Project 3"); //title
        setLocationRelativeTo(null); //launch in the middle of the screen
        //setSize(375,200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(6,2,10,10));
        myPanel.add(new JLabel(""));
        myPanel.add(iterativeRadio);
        myPanel.add(new JLabel(""));
        myPanel.add(recursiveRadio);
        myPanel.add(new JLabel("Enter n:"));
        myPanel.add(numField);
        numField.setEditable(true);
        myPanel.add(new JLabel(""));
        myPanel.add(computeButton);
        myPanel.add(new JLabel("Result:"));
        myPanel.add(resultField);
        resultField.setEditable(false);
        myPanel.add(new JLabel("Efficiency:"));
        myPanel.add(efficiencyField);
        efficiencyField.setEditable(false);
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(iterativeRadio);
        radioGroup.add(recursiveRadio);
        iterativeRadio.setSelected(true); //set default radio button to be selected

        //add the panel with everything on it and pack it to size
        add(myPanel);
        pack();

        //create action listener for the button
        computeButton.addActionListener(event -> {
            try {
                if(iterativeRadio.isSelected()) {
                    int myValue;
                    myValue = Sequence.computeIterative(Integer.parseInt(numField.getText()));
                    resultField.setText(Integer.toString(myValue));
                    efficiencyField.setText(Integer.toString(Sequence.getEfficiency()));
                } else { //else here handles the other radio button
                    int myValue2;
                    myValue2 = Sequence.computeRecursive(Integer.parseInt(numField.getText()));
                    resultField.setText(Integer.toString(myValue2));
                    efficiencyField.setText(Integer.toString(Sequence.getEfficiency()));
                }
            } catch(NumberFormatException e) { //catch something other than a number being entered
                JOptionPane.showMessageDialog(null, "You must enter a number!");
            } //end of catch()
        }); //end of actionListener()

        //create and add the window listener
        addWindowListener(new WindowClass());
    } //end of constructor

    //Inner class to extend WindowAdapter
    private static class WindowClass extends WindowAdapter {
        //empty constructor for good coding practice
        public WindowClass() {
        }

        @Override //method to perform when the window is closed
        public void windowClosed(WindowEvent e) {
            try {
                String fileContent = "\'n\' Value, Iterative Efficiency, Recursive Efficiency\n";

                //the file writes out to the current directory the program is running from
                BufferedWriter myWriter = new BufferedWriter(new FileWriter("outputFile.csv"));
                myWriter.write(fileContent); //write the String 'fileContent' to make the headers in the csv file
                int iterateEfficiency;
                int recurseEfficiency;
                //for loop to go through to calculate efficiency for n(0-10) of both computation methods
                for(int i = 0; i < 11; i++) {
                    Sequence.computeIterative(i);
                    iterateEfficiency = Sequence.getEfficiency();
                    Sequence.computeRecursive(i);
                    recurseEfficiency = Sequence.getEfficiency();
                    myWriter.write(i + "," + iterateEfficiency + "," + recurseEfficiency + "\n");
                }
                myWriter.close();
            } catch(java.io.IOException e2) {
                System.out.println("An IOException has occurred.");
            }
        }
    }

    public static void main(String[] args) {
        //create window and set to visible
        ProgramGUI window = new ProgramGUI();
        window.setVisible(true);
    }
}
