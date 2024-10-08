package com.example.taskmanager;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        // Adding tasks
        taskManager.addTask(new Task(1, "Complete project", "Finish the task manager project", LocalDate.of(2024, 10, 10), 1));
        taskManager.addTask(new Task(2, "Buy groceries", "Get milk, eggs, and bread", LocalDate.of(2024, 10, 8), 2));

        // Viewing all tasks
        System.out.println("All Tasks:");
        taskManager.getAllTasks().forEach(System.out::println);

        // Updating a task
        taskManager.updateTask(1, "Complete project with tests", "Finish the project and write unit tests", 1, LocalDate.of(2024, 10, 12));

        // Deleting a task
        taskManager.deleteTask(2);

        // Viewing all tasks after update and delete
        System.out.println("\nUpdated Tasks:");
        taskManager.getAllTasks().forEach(System.out::println);
    }
}
