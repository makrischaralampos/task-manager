package com.example.taskmanager;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        // Adding tasks
        taskManager.addTask(new Task(1, "Complete project", "Finish the task manager project", LocalDate.of(2024, 10, 10), 1, "Pending"));
        taskManager.addTask(new Task(2, "Buy groceries", "Get milk, eggs, and bread", LocalDate.of(2024, 10, 8), 2, "In Progress"));

        // Viewing all tasks
        System.out.println("All Tasks:");
        taskManager.getAllTasks().forEach(System.out::println);

        // Updating a task
        taskManager.updateTask(1, "Complete project with tests", "Finish the project and write unit tests", LocalDate.of(2024, 10, 12), 1, "In Progress");

        // Filtering tasks by status
        System.out.println("\nTasks with status 'In Progress':");
        taskManager.getTasksByStatus("In Progress").forEach(System.out::println);

        // Deleting a task
        taskManager.deleteTask(2);

        // Viewing all tasks after update and delete
        System.out.println("\nUpdated Tasks:");
        taskManager.getAllTasks().forEach(System.out::println);
    }
}
