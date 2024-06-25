/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.login;

/**
 *
 * @author Sukoluhle Ndlovu
 */

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


class Task {
    private static int taskCounter = 0;
    private static List<Task> tasks = new ArrayList<>();

    private String taskName;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    private String taskStatus;
    private String taskID;

    public Task(String taskName, String taskDescription, String developerDetails, int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = createTaskID();
        tasks.add(this);
    }

    public boolean checkTaskDescription() {
        return this.taskDescription.length() <= 50;
    }

    public String createTaskID() {
        String taskNamePrefix = this.taskName.length() >= 2 ? this.taskName.substring(0, 2).toUpperCase() : this.taskName.toUpperCase();
        String developerSuffix = this.developerDetails.length() >= 3 ? this.developerDetails.substring(this.developerDetails.length() - 3).toUpperCase() : this.developerDetails.toUpperCase();
        return taskNamePrefix + ":" + taskCounter++ + ":" + developerSuffix;
    }

    public String getTaskID() {
        return this.taskID;
    }

    public String printTaskDetails() {
        return "Task Status: " + this.taskStatus + "\n" +
               "Developer Details: " + this.developerDetails + "\n" +
               "Task Number: " + (taskCounter - 1) + "\n" +
               "Task Name: " + this.taskName + "\n" +
               "Task Description: " + this.taskDescription + "\n" +
               "Task ID: " + this.taskID + "\n" +
               "Task Duration: " + this.taskDuration + " hours";
    }

    public static int returnTotalHours() {
        return tasks.stream().mapToInt(task -> task.taskDuration).sum();
    }

    public static void resetTaskCounter() {
        taskCounter = 0;
    }

    public static List<String> getDevelopers() {
        List<String> developers = new ArrayList<>();
        for (Task task : tasks) {
            developers.add(task.developerDetails);
        }
        return developers;
    }

    public static List<String> getTaskNames() {
        List<String> taskNames = new ArrayList<>();
        for (Task task : tasks) {
            taskNames.add(task.taskName);
        }
        return taskNames;
    }

    public static List<String> getTaskIDs() {
        List<String> taskIDs = new ArrayList<>();
        for (Task task : tasks) {
            taskIDs.add(task.taskID);
        }
        return taskIDs;
    }

    public static List<Integer> getTaskDurations() {
        List<Integer> taskDurations = new ArrayList<>();
        for (Task task : tasks) {
            taskDurations.add(task.taskDuration);
        }
        return taskDurations;
    }

    public static List<String> getTaskStatuses() {
        List<String> taskStatuses = new ArrayList<>();
        for (Task task : tasks) {
            taskStatuses.add(task.taskStatus);
        }
        return taskStatuses;
    }

    public static String getAllTaskDetails() {
        StringBuilder report = new StringBuilder();
        for (Task task : tasks) {
            report.append(task.printTaskDetails()).append("\n\n");
        }
        return report.toString();
    }

}