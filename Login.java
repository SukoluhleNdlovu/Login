/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.login;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;
/**
 *
 * @author Sukoluhle Ndlovu
 */

class Login {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public Login(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        return password.length() >= 8 &&
               Pattern.compile("[A-Z]").matcher(password).find() &&
               Pattern.compile("[0-9]").matcher(password).find() &&
               Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
    }

    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        } else if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        } else {
            return "User successfully registered.";
        }
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }

    public String returnLoginStatus(String enteredUsername, String enteredPassword) {
        if (loginUser(enteredUsername, enteredPassword)) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    public static void main(String[] args) {
        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");
        String username = JOptionPane.showInputDialog("Enter your username (must contain an underscore and be no more than 5 characters long):");
        String password = JOptionPane.showInputDialog("Enter your password (at least 8 characters, contain a capital letter, a number, and a special character):");

        Login user = new Login(firstName, lastName, username, password);

        String registrationMessage = user.registerUser();
        JOptionPane.showMessageDialog(null, registrationMessage);

        if (registrationMessage.equals("User successfully registered.")) {
            String loginUsername = JOptionPane.showInputDialog("Enter your username to login:");
            String loginPassword = JOptionPane.showInputDialog("Enter your password to login:");

            String loginMessage = user.returnLoginStatus(loginUsername, loginPassword);
            JOptionPane.showMessageDialog(null, loginMessage);

            if (loginMessage.contains("Welcome")) {
                OUTER:
                while (true) {
                    String option = JOptionPane.showInputDialog("Welcome to EasyKanban\n1. Add tasks\n2. Show report\n3. Quit\nChoose an option:");
                    switch (option) {
                        case "1" -> {
                            String numTasksStr = JOptionPane.showInputDialog("How many tasks do you wish to enter?");
                            int numTasks = Integer.parseInt(numTasksStr);
                            for (int i = 0; i < numTasks; i++) {
                                String taskName = JOptionPane.showInputDialog("Enter task name:");
                                String taskDescription = JOptionPane.showInputDialog("Enter task description (50 characters max):");
                                String developerDetails = JOptionPane.showInputDialog("Enter developer details (first and last name):");
                                String taskDurationStr = JOptionPane.showInputDialog("Enter task duration (hours):");
                                int taskDuration = Integer.parseInt(taskDurationStr);
                                String taskStatus = JOptionPane.showInputDialog("Enter task status (To Do, Doing, Done):");
                                
                                Task task = new Task(taskName, taskDescription, developerDetails, taskDuration, taskStatus);
                                
                                if (task.checkTaskDescription()) {
                                    JOptionPane.showMessageDialog(null, "Task successfully captured");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
                                }
                                
                                JOptionPane.showMessageDialog(null, task.printTaskDetails());
                            }   JOptionPane.showMessageDialog(null, "Total hours of all tasks: " + Task.returnTotalHours() + " hours");
                        }
                        case "2" -> 
                            JOptionPane.showMessageDialog(null, Task.getAllTaskDetails());
                        case "3" -> {
                            JOptionPane.showMessageDialog(null, "Exiting EasyKanban. Goodbye!");
                            break OUTER;
                        }
                        default -> JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
                    }
                }
            }
        }
    }
}