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
            System.out.println("\nChoose an action: add, update, delete, view, sort, filter, or exit: ");
            String action = scanner.nextLine();

            if (action.equalsIgnoreCase("exit")) {
                break;
            }

            if (action.equalsIgnoreCase("add")) {
                addTasksInteractively(taskManager, scanner);
            } else if (action.equalsIgnoreCase("update")) {
                updateTaskInteractively(taskManager, scanner);
            } else if (action.equalsIgnoreCase("delete")) {
                deleteTaskInteractively(taskManager, scanner);
            } else if (action.equalsIgnoreCase("view")) {
                displayTasks(taskManager.getAllTasks());
            } else if (action.equalsIgnoreCase("sort")) {
                sortTasksInteractively(taskManager, scanner);
            } else if (action.equalsIgnoreCase("filter")) {
                System.out.println("Filter tasks by: (high priority/this week): ");
                String filterOption = scanner.nextLine();

                if (filterOption.equalsIgnoreCase("high priority")) {
                    displayTasks(taskManager.filterByHighPriority());
                } else if (filterOption.equalsIgnoreCase("this week")) {
                    displayTasks(taskManager.filterByDueThisWeek());
                } else {
                    System.out.println("Invalid filter option");
                }
            } else {
                System.out.println("Invalid action. Please try again.");
            }
        }
    }

    private static void updateTaskInteractively(TaskManager taskManager, Scanner scanner) {
        System.out.print("Enter Task ID: ");
        int id = -1;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Task ID. Please enter a valid number.");
        }

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
        }

        System.out.print("New Priority (1 = High, 2 = Medium, 3 = Low): ");
        int newPriority = -1;
        try {
            newPriority = Integer.parseInt(scanner.nextLine());
            if (newPriority < 1 || newPriority > 3) {
                System.out.println("Error: Priority must be between 1 and 3.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid priority. Please enter a number between 1 and 3.");
        }

        System.out.print("New Status (Pending, In Progress, Completed): ");
        String newStatus = scanner.nextLine();

        boolean updated = taskManager.updateTask(id, newTitle, newDescription, newDueDate, newPriority, newStatus);
        if (updated) {
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Task not found!");
        }
    }

    private static void deleteTaskInteractively(TaskManager taskManager, Scanner scanner) {
        System.out.print("Enter Task ID: ");
        int id = -1;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Task ID. Please enter a valid number.");
        }

        boolean deleted = taskManager.deleteTask(id);
        if (deleted) {
            System.out.println("Task deleted successfully!");
        } else {
            System.out.println("Task not found!");
        }
    }

    private static void sortTasksInteractively(TaskManager taskManager, Scanner scanner) {
        System.out.println("Sort tasks by: (priority/due date): ");
        String sortOption = scanner.nextLine();

        if (sortOption.equalsIgnoreCase("priority")) {
            displayTasks(taskManager.sortByPriority());
        } else if (sortOption.equalsIgnoreCase("due date")) {
            displayTasks(taskManager.sortByDueDate());
        } else {
            System.out.println("Invalid sort option.");
        }
    }
}
