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
    public boolean updateTask(int id, String newTitle, String newDescription, LocalDate newDueDate, int newPriority, String newStatus) {
        Optional<Task> taskOptional = getTaskById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.updateTask(newTitle, newDescription, newDueDate, newPriority, newStatus);
            return true;
        }
        return false;
    }

    // Delete a task
    public boolean deleteTask(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }

    // Find all tasks with a specific status
    public List<Task> getTasksByStatus(String status) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getStatus().equalsIgnoreCase(status)) {
                result.add(task);
            }
        }
        return result;
    }
}
