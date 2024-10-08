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

    public Task(int id, String title, String description, LocalDate dueDate, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
    }
}
