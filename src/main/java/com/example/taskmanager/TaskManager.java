package com.example.taskmanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    // Add a new task
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return tasks;
    }

    // Find task by ID
    public Optional<Task> getTaskById(int id) {
        return tasks.stream().filter(task -> task.getId() == id).findFirst();
    }

    // Update a task
    public boolean updateTask(int id, String newTitle, String newDescription, int newPriority, LocalDate newDueDate) {
        Optional<Task> taskOptional = getTaskById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setTitle(newTitle);
            task.setDescription(newDescription);
            task.setPriority(newPriority);
            task.setDueDate(newDueDate);
            return true;
        }
        return false;
    }

    // Delete a task
    public boolean deleteTask(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }
}
