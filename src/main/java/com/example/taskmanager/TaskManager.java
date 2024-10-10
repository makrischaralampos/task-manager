package com.example.taskmanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    // Add a new task
    public void addTask(Task task) {
        if (task.getDescription() != null && task.getTitle() != null) {
            tasks.add(task);
        } else {
            throw new IllegalArgumentException("Invalid title or description. Please enter a valid title and description.");
        }
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
            if (newTitle != null) {
                task.updateTask(newTitle, newDescription, newDueDate, newPriority, newStatus);
                return true;
            } else {
                throw new IllegalArgumentException("Invalid title. Please enter a valid title.");
            }
        } else {
            System.out.println("Error: Task with ID " + id + " does not exist.");
            return false;
        }
    }

    // Delete a task
    public boolean deleteTask(int id) {
        boolean removed = tasks.removeIf(task -> task.getId() == id);
        if (!removed) {
            System.out.println("Error: Task with ID " + id + " does not exist.");
        }
        return removed;
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

    // Method to sort tasks by priority
    public List<Task> sortByPriority() {
        return tasks.stream()
                .sorted(Comparator.comparingInt(Task::getPriority))
                .collect(Collectors.toList());
    }

    // Method to sort tasks by due date
    public List<Task> sortByDueDate() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .collect(Collectors.toList());
    }

    // Method to filter tasks by high priority (Priority 1)
    public List<Task> filterByHighPriority() {
        return tasks.stream()
                .filter(task -> task.getPriority() == 1)
                .collect(Collectors.toList());
    }

    // Method to filter tasks due within the next week
    public List<Task> filterByDueThisWeek() {
        LocalDate today = LocalDate.now();
        LocalDate oneWeekLater = today.plusDays(7);
        return tasks.stream()
                .filter(task -> !task.getDueDate().isBefore(today) && !task.getDueDate().isAfter(oneWeekLater))
                .collect(Collectors.toList());
    }
}
