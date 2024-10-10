package com.example.taskmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    public void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    public void testAddTask() {
        Task task = new Task(1, "Task 1", "Description 1", LocalDate.now(), 1, "Pending");
        taskManager.addTask(task);
        assertEquals(1, taskManager.getAllTasks().size());
        assertEquals(task, taskManager.getAllTasks().get(0));
    }

    @Test
    public void testUpdateTask() {
        Task task = new Task(1, "Task 1", "Description 1", LocalDate.now(), 1, "Pending");
        taskManager.addTask(task);

        boolean updated = taskManager.updateTask(1, "Updated Task 1", "Updated Description", LocalDate.now().plusDays(2), 2, "In Progress");
        assertTrue(updated);

        Task updatedTask = taskManager.getAllTasks().get(0);
        assertEquals("Updated Task 1", updatedTask.getTitle());
        assertEquals("Updated Description", updatedTask.getDescription());
        assertEquals(LocalDate.now().plusDays(2), updatedTask.getDueDate());
        assertEquals(2, updatedTask.getPriority());
        assertEquals("In Progress", updatedTask.getStatus());
    }

    @Test
    public void testDeleteTask() {
        Task task = new Task(1, "Task 1", "Description 1", LocalDate.now(), 1, "Pending");
        taskManager.addTask(task);

        boolean deleted = taskManager.deleteTask(1);
        assertTrue(deleted);
        assertEquals(0, taskManager.getAllTasks().size());
    }

    @Test
    public void testDeleteNonExistentTask() {
        boolean deleted = taskManager.deleteTask(999);
        assertFalse(deleted);
    }

    @Test
    public void testSortByPriority() {
        Task task1 = new Task(1, "Task 1", "Description 1", LocalDate.now(), 2, "Pending");
        Task task2 = new Task(2, "Task 2", "Description 2", LocalDate.now(), 1, "Pending");
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        List<Task> sortedTasks = taskManager.sortByPriority();
        assertEquals(2, sortedTasks.size());
        assertEquals(task2, sortedTasks.get(0)); // Task 2 has higher priority
    }

    @Test
    public void testSortByDueDate() {
        Task task1 = new Task(1, "Task 1", "Description 1", LocalDate.now().plusDays(5), 2, "Pending");
        Task task2 = new Task(2, "Task 2", "Description 2", LocalDate.now().plusDays(2), 1, "Pending");
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        List<Task> sortedTasks = taskManager.sortByDueDate();
        assertEquals(2, sortedTasks.size());
        assertEquals(task2, sortedTasks.get(0)); // Task 2 has earlier due date
    }

    @Test
    public void testFilterByHighPriority() {
        Task task1 = new Task(1, "Task 1", "Description 1", LocalDate.now(), 1, "Pending");
        Task task2 = new Task(2, "Task 2", "Description 2", LocalDate.now(), 2, "Pending");
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        List<Task> filteredTasks = taskManager.filterByHighPriority();
        assertEquals(1, filteredTasks.size());
        assertEquals(task1, filteredTasks.get(0));
    }

    @Test
    public void testFilterByDueThisWeek() {
        Task task1 = new Task(1, "Task 1", "Description 1", LocalDate.now().plusDays(3), 1, "Pending");
        Task task2 = new Task(2, "Task 2", "Description 2", LocalDate.now().plusDays(10), 1, "Pending");
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        List<Task> filteredTasks = taskManager.filterByDueThisWeek();
        assertEquals(1, filteredTasks.size());
        assertEquals(task1, filteredTasks.get(0));
    }
}
