package com.example.taskmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    void testAddTask() {
        Task task = new Task(1, "Test Task", "Description", LocalDate.now(), 1, "Pending");
        taskManager.addTask(task);
        List<Task> tasks = taskManager.getAllTasks();
        assertEquals(1, tasks.size());
        assertEquals("Test Task", tasks.get(0).getTitle());
    }

    @Test
    void testUpdateTask() {
        Task task = new Task(1, "Test Task", "Description", LocalDate.now(), 1, "Pending");
        taskManager.addTask(task);

        boolean isUpdated = taskManager.updateTask(1, "Updated Task", "Updated Description", LocalDate.now().plusDays(2), 2, "In Progress");
        assertTrue(isUpdated);

        Task updatedTask = taskManager.getTaskById(1).orElse(null);
        assertNotNull(updatedTask);
        assertEquals("Updated Task", updatedTask.getTitle());
        assertEquals("In Progress", updatedTask.getStatus());
    }

    @Test
    void testDeleteTask() {
        Task task = new Task(1, "Test Task", "Description", LocalDate.now(), 1, "Pending");
        taskManager.addTask(task);

        boolean isDeleted = taskManager.deleteTask(1);
        assertTrue(isDeleted);
        assertTrue(taskManager.getAllTasks().isEmpty());
    }

    @Test
    void testUpdateNonExistentTask() {
        boolean isUpdated = taskManager.updateTask(999, "Non-existent Task", "No Description", LocalDate.now(), 1, "Pending");
        assertFalse(isUpdated);
    }

    @Test
    void testDeleteNonExistentTask() {
        boolean isDeleted = taskManager.deleteTask(999);
        assertFalse(isDeleted);
    }
}
