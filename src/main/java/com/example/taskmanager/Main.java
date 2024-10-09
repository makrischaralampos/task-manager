package com.example.taskmanager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        // Adding tasks interactively
        addTasksInteractively(taskManager, scanner);

        // Display all tasks
        displayTasks(taskManager.getAllTasks());

        // Allow users to update or delete tasks
        manageTasksInteractively(taskManager, scanner);

        scanner.close();
    }

    // Method to add tasks interactively
    private static void addTasksInteractively(TaskManager taskManager, Scanner scanner) {
        while (true) {
            System.out.println("\nEnter a new task:");

            System.out.print("Task ID: ");
            int id = -1;
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid Task ID. Please enter a valid number.");
                continue;
            }

            System.out.print("Title: ");
            String title = scanner.nextLine();

            System.out.print("Description: ");
            String description = scanner.nextLine();

            System.out.print("Due Date (YYYY-MM-DD): ");
            LocalDate dueDate = null;
            try {
                dueDate = LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
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

    // Method to manage (update/delete) tasks interactively
    private static void manageTasksInteractively(TaskManager taskManager, Scanner scanner) {
        while (true) {
            System.out.println("\nDo you want to update or delete a task? (update/delete/exit): ");
            String action = scanner.nextLine();

            if (action.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Enter Task ID: ");
            int id = -1;
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid Task ID. Please enter a valid number.");
                continue;
            }

            if (action.equalsIgnoreCase("update")) {
                // Update task
                System.out.print("New Title: ");
                String newTitle = scanner.nextLine();

                System.out.print("New Description: ");
                String newDescription = scanner.nextLine();

                System.out.print("New Due Date (YYYY-MM-DD): ");
                LocalDate newDueDate = null;
                try {
                    newDueDate = LocalDate.parse(scanner.nextLine());
                } catch (DateTimeParseException e) {
                    System.out.println("Error: Invalid date format. Please use YYYY-MM-DD.");
                    continue;
                }

                System.out.print("New Priority (1 = High, 2 = Medium, 3 = Low): ");
                int newPriority = -1;
                try {
                    newPriority = Integer.parseInt(scanner.nextLine());
                    if (newPriority < 1 || newPriority > 3) {
                        System.out.println("Error: Priority must be between 1 and 3.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid priority. Please enter a number between 1 and 3.");
                    continue;
                }

                System.out.print("New Status (Pending, In Progress, Completed): ");
                String newStatus = scanner.nextLine();

                boolean updated = taskManager.updateTask(id, newTitle, newDescription, newDueDate, newPriority, newStatus);
                if (updated) {
                    System.out.println("Task updated successfully!");
                } else {
                    System.out.println("Task not found!");
                }
            } else if (action.equalsIgnoreCase("delete")) {
                // Delete task
                boolean deleted = taskManager.deleteTask(id);
                if (deleted) {
                    System.out.println("Task deleted successfully!");
                } else {
                    System.out.println("Task not found!");
                }
            } else {
                System.out.println("Invalid action. Please choose 'update' or 'delete'.");
            }

            // Display updated tasks
            displayTasks(taskManager.getAllTasks());
        }
    }
}
