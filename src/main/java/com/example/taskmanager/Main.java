package com.example.taskmanager;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        // Adding tasks interactively
        while (true) {
            System.out.println("\nEnter a new task:");

            System.out.print("Task ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Title: ");
            String title = scanner.nextLine();

            System.out.print("Description: ");
            String description = scanner.nextLine();

            System.out.print("Due Date (YYYY-MM-DD): ");
            LocalDate dueDate = null;
            try {
                dueDate = LocalDate.parse(scanner.nextLine());
            } catch (DateTimeException e) {
                System.out.print("Invalid date format. Please try again.");
                continue;
            }

            System.out.print("Priority (1 = High, 2 = Medium, 3 = Low): ");
            int priority = Integer.parseInt(scanner.nextLine());

            System.out.print("Status (Pending, In Progress, Completed): ");
            String status = scanner.nextLine();

            // Add task to the task manager
            Task newTask = new Task(id, title, description, dueDate, priority, status);
            taskManager.addTask(newTask);
            System.out.println("Task added successfully!");

            System.out.print("Do you want to add another task? (yes/no): ");
            String addAnother = scanner.nextLine();
            if (!addAnother.equalsIgnoreCase("yes")) {
                break;
            }
        }

        // Display all tasks
        System.out.println("\nAll Tasks:");
        // Display tasks in a table-like format
        displayTasks(taskManager.getAllTasks());

        scanner.close();
    }

    // Method to display tasks in a table format
    public static void displayTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        // Header
        System.out.printf("%-5s %-20s %-30s %-15s %-10s %-15s %-15s\n", "ID", "Title", "Description", "Due Date", "Priority", "Status", "Created Date");
        System.out.println("--------------------------------------------------------------------------------------------------------------");

        // Date formatter for displaying dates
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Iterate and print each task
        for (Task task : tasks) {
            System.out.printf(
                    "%-5d %-20s %-30s %-15s %-10d %-15s %-15s\n",
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getDueDate().format(dateFormatter),
                    task.getPriority(),
                    task.getStatus(),
                    task.getCreatedDate().format(dateFormatter)
            );
        }
    }
}
