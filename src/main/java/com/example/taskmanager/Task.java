package com.example.taskmanager;

import lombok.Data;

import java.time.LocalDate;

@Data // Lombok annotation to generate getters, setters, and constructor
public class Task {
    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private int priority; // 1 = High, 2 = Medium, 3 = Low
    private String status; // e.g., "Pending", "In Progress", "Completed"
    private LocalDate createdDate;
    private LocalDate updatedDate;

    public Task(int id, String title, String description, LocalDate dueDate, int priority, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
        this.createdDate = LocalDate.now(); // Set created date to current date
        this.updatedDate = LocalDate.now(); // Set updated date to current date
    }

    // Method to update the task's status
    public void updateTask(String title, String description, LocalDate dueDate, int priority, String status) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
        this.updatedDate = LocalDate.now(); // Update the updated date when task is modified
    }
}
