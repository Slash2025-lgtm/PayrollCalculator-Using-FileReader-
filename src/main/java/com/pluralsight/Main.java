package com.pluralsight;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean opened = true;
        while (opened) {
            try {
                Employee employee = new Employee();
                Scanner keyboard = new Scanner(System.in);
                System.out.print("Would you like to:\n[1] Show Current Payroll \n[2] Edit A file\n[3] Transfer\n[ANY Other Number Key] Exit\nEnter in a Number: ");
                int chosen = keyboard.nextInt();
                keyboard.nextLine();
                if (chosen == 1) {
                    showCurrentPayroll(employee);
                } else if (chosen == 2) {
                    edit(employee, keyboard);
                } else if (chosen == 3) {
                    transfer(employee, keyboard);
                } else {
                    opened = false;
                }

            } catch (Exception e) {
                System.out.println("I/O Error \n[1] See Error\n[2] Exit\nEnter Number: ");
                Scanner keyboard = new Scanner(System.in);
                int selected = keyboard.nextInt();

                if (selected == 1) {
                    e.printStackTrace();
                } else if (selected == 2) {
                    System.out.println("Ending Program");
                }
            }
        }
    }
    public static void showCurrentPayroll(Employee employee) {
        try {
            FileReader fileReader = new FileReader("src/main/resources/employees.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input;
            while ((input = bufReader.readLine()) != null) {
                String[] employeeList = input.split("[|]");

                if (!employeeList[0].equalsIgnoreCase("id")) {
                    employee.setEmployeeId(Integer.parseInt(employeeList[0]));
                    employee.setName(employeeList[1]);
                    employee.setHoursWorked(Double.parseDouble(employeeList[2]));
                    employee.setPayRate(Double.parseDouble(employeeList[3]));

                    String name = employee.getName();
                    int employeeID = employee.getEmployeeId();
                    double workHours = employee.getHoursWorked();
                    double payRate = employee.getPayRate();
                    double grossPay = employee.getGrossPay();

                    System.out.printf("ID: %d\nName: %s\nGross Pay: $%.2f",employeeID, name ,grossPay);
                    System.out.println("\n");
                }
            }
            bufReader.close();
        } catch (IOException e) {
            System.out.println("I/O Error \n[1] See Error\n[2] Exit\nEnter Number: ");
            Scanner keyboard = new Scanner(System.in);
            int selected = keyboard.nextInt();

            if (selected == 1) {
                e.printStackTrace();
            } else if (selected == 2) {
                System.out.println("Ending Program");
            }
        }
    }

    public static void edit(Employee employee, Scanner keyboard) {
        try {
            System.out.print("Enter the name of the file: ");
            String fileName = keyboard.next().trim();
            keyboard.nextLine();
            if (fileName.equalsIgnoreCase("employees.csv")) {
                System.out.println("=== Edit Mode ===");

                FileWriter fileWriter = new FileWriter("src/main/resources/employees.csv", true);
                BufferedWriter bufWriter = new BufferedWriter(fileWriter);

                System.out.println("Enter in the Employee's ID: ");
                String id = keyboard.nextLine();

                System.out.println("Enter in the Employee's Name: ");
                String name = keyboard.nextLine();

                System.out.println("Enter in the Employee's Hourly pay: ");
                String payRate = keyboard.nextLine();

                System.out.println("Enter in the Employee's Worked Hours: ");
                String workedHours = keyboard.nextLine();

                System.out.println(name + " Has been added to the payroll Calculator.");
                bufWriter.write("\n" + id + "|" + name + "|" + payRate + "|" + workedHours);
                bufWriter.close();
            }
        } catch (IOException e) {
            System.out.println("I/O Error \n[1] See Error\n[2] Exit\nEnter Number: ");
            int selected = keyboard.nextInt();

            if (selected == 1) {
                e.printStackTrace();
            } else if (selected == 2) {
                System.out.println("Ending Program");
            }
        }
    }
    public static void transfer(Employee employee, Scanner keyboard) {
        try {
            System.out.print("Enter the name of the file employee file to process: ");
            String fileName = keyboard.next();
            keyboard.nextLine();

            FileReader fileReader = new FileReader("src/main/resources/" + fileName);
            BufferedReader bufReader = new BufferedReader(fileReader);

            System.out.print("Enter the name of the payroll file to create: ");
            String newfileName = keyboard.next();
            keyboard.nextLine();

            FileWriter fileWriter = new FileWriter("src/main/resources/" + newfileName);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            String input;

            while ((input = bufReader.readLine()) != null) {
                String[] employeeList = input.split("[|]");

                if (!employeeList[0].equalsIgnoreCase("id")) {
                    employee.setEmployeeId(Integer.parseInt(employeeList[0]));
                    employee.setName(employeeList[1]);
                    employee.setHoursWorked(Double.parseDouble(employeeList[2]));
                    employee.setPayRate(Double.parseDouble(employeeList[3]));

                    String name = employee.getName();
                    int employeeID = employee.getEmployeeId();
                    double workHours = employee.getHoursWorked();
                    double payRate = employee.getPayRate();
                    double grossPay = employee.getGrossPay();

                    String[] fileType = newfileName.split("[.]");

                    String format = "";

                    if (fileType[1].equalsIgnoreCase("csv")) {
                       format = employeeID + "|" + name + "|$" + grossPay + "\n";
                    } else {
                        format = "{ id: " + employeeID + " Name :" + name + "grossPay :" + grossPay + "},\n";
                    }
                    bufWriter.write(format);
                }
            }
            System.out.println("\n");
            bufWriter.close();
        } catch (IOException e) {
            System.out.println("I/O Error \n[1] See Error\n[2] Exit\nEnter Number: ");
            int selected = keyboard.nextInt();

            if (selected == 1) {
                e.printStackTrace();
            } else if (selected == 2) {
                System.out.println("Ending Program");
            }
        }
    }
}