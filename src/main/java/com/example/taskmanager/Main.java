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

        System.out.println("Welcome to the Task Management App!");
        manageTasksInteractively(taskManager, scanner);

        scanner.close();
    }

    // Method to manage (update/delete) tasks interactively
    private static void manageTasksInteractively(TaskManager taskManager, Scanner scanner) {
        while (true) {
            // Display available actions
            System.out.println("\nChoose an action: ");
            System.out.println("1. Add a task");
            System.out.println("2. View all tasks");
            System.out.println("3. Update a task");
            System.out.println("4. Delete a task");
            System.out.println("5. Sort tasks");
            System.out.println("6. Filter tasks");
            System.out.println("7. Exit");

            String action = scanner.nextLine();

            switch (action) {
                case "1":
                    addTaskInteractively(taskManager, scanner);
                    break;
                case "2":
                    displayTasks(taskManager.getAllTasks());
                    break;
                case "3":
                    updateTaskInteractively(taskManager, scanner);
                    break;
                case "4":
                    deleteTaskInteractively(taskManager, scanner);
                    break;
                case "5":
                    sortTasksInteractively(taskManager, scanner);
                    break;
                case "6":
                    filterTasksInteractively(taskManager, scanner);
                    break;
                case "7":
                    System.out.println("Exiting the app. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid action. Please choose a number between 1 and 7.");
            }
        }
    }

    // Method to add tasks interactively
    private static void addTaskInteractively(TaskManager taskManager, Scanner scanner) {
        System.out.println("Enter task ID: ");
        int id = -1;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Task ID. Please enter a valid number.");
        }

            System.out.println("Enter task title: ");
            String title = scanner.nextLine();

            System.out.println("Enter task description: ");
            String description = scanner.nextLine();

            System.out.println("Enter due date (YYYY-MM-DD): ");
            LocalDate dueDate = null;
            try {
                dueDate = LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }

            System.out.println("Enter task priority (1 = High, 3 = Low): ");
        int priority = -1;
        try {
            priority = Integer.parseInt(scanner.nextLine());
            if (priority < 1 || priority > 3) {
                System.out.println("Error: Priority must be between 1 and 3.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid priority. Please enter a number between 1 and 3.");
        }

            System.out.println("Enter task status (e.g., Pending, In Progress, Completed): ");
            String status = scanner.nextLine();

            // Add task to the task manager
            Task newTask = new Task(id, title, description, dueDate, priority, status);
            taskManager.addTask(newTask);

            System.out.println("Task added successfully!");
    }

    private static void updateTaskInteractively(TaskManager taskManager, Scanner scanner) {
        System.out.println("Enter the task ID to update: ");
        int taskId = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter new task title: ");
        String title = scanner.nextLine();

        System.out.println("Enter new task description: ");
        String description = scanner.nextLine();

        System.out.println("Enter new due date (YYYY-MM-DD): ");
        LocalDate dueDate = null;
        try {
            dueDate = LocalDate.parse(scanner.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use YYYY-MM-DD.");
        }

        System.out.println("Enter new task priority (1 = High, 3 = Low): ");
        int priority = -1;
        try {
            priority = Integer.parseInt(scanner.nextLine());
            if (priority < 1 || priority > 3) {
                System.out.println("Error: Priority must be between 1 and 3.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid priority. Please enter a number between 1 and 3.");
        }

        System.out.println("Enter new task status (e.g., Pending, In Progress, Completed): ");
        String status = scanner.nextLine();

        boolean updated = taskManager.updateTask(taskId, title, description, dueDate, priority, status);

        if (updated) {
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Task with the given ID not found.");
        }
    }

    private static void deleteTaskInteractively(TaskManager taskManager, Scanner scanner) {
        System.out.print("Enter the task ID to delete: ");
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
            System.out.println("Task with the given ID not found.");
        }
    }

    private static void sortTasksInteractively(TaskManager taskManager, Scanner scanner) {
        System.out.println("Sort tasks by: ");
        System.out.println("1. Priority");
        System.out.println("2. Due date");

        String sortOption = scanner.nextLine();

        switch (sortOption) {
            case "1":
                displayTasks(taskManager.sortByPriority());
                break;
            case "2":
                displayTasks(taskManager.sortByDueDate());
                break;
            default:
                System.out.println("Invalid sort option.");
        }
    }

    private static void filterTasksInteractively(TaskManager taskManager, Scanner scanner) {
        System.out.println("Filter tasks by: ");
        System.out.println("1. High priority");
        System.out.println("2. Due this week");

        String filterOption = scanner.nextLine();

        switch (filterOption) {
            case "1":
                displayTasks(taskManager.filterByHighPriority());
                break;
            case "2":
                displayTasks(taskManager.filterByDueThisWeek());
                break;
            default:
                System.out.println("Invalid filter option.");
        }
    }

    // Method to display tasks in a table format
    private static void displayTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
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
}
