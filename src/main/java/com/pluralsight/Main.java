package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("src/main/resources/employees.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input;
            String[] labelList = new String[5];
            boolean Labels = false;
            while ((input = bufReader.readLine()) != null) {
                String[] employeeList = input.split("[|]");

                if (!Labels) {
                    int i = 0;
                    while (i < employeeList.length) {
                        labelList[i] = employeeList[i];
                        i++;
                    }
                    Labels = true;
                } else {
                    int i = 0;
                    while (i < employeeList.length) {
                        System.out.println(labelList[i] + ": " + employeeList[i]);
                        i++;
                    }
                    double hours = Double.parseDouble(employeeList[2]);
                    double payRate = Double.parseDouble(employeeList[3]);
                    System.out.printf("gross-pay: $%.2f\n", (payRate * hours));
                    System.out.println("\n");
                }

            }

        } catch (IOException e) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("I/O Error \n[1] See Error\n[2] Exit\nEnter Number: ");
            int Selected = keyboard.nextInt();

            if (Selected == 1) {
                e.printStackTrace();
            } else if (Selected == 2) {
                System.out.println("Ending Program");
            }
        }


    }
}
