# Task Management App

## Overview

The **Task Management App** is a Java-based command-line application that allows users to manage tasks efficiently.
Users can add, update, delete and view tasks,
with additional features like sorting and filtering tasks based on priority and due date.

This project is built using **Maven** for dependency management and build automation.
The app includes unit tests to ensure all core operations function correctly.

## Features

- Add tasks with title, description, due date, and priority.
- Update existing tasks.
- Delete tasks.
- View all tasks.
- Sort tasks by priority or due date.
- Filter tasks by high priority or tasks due within the next week.
- Interactive command-line interface to perform operations.

## Technologies Used

- Java
- Maven
- JUnit (for unit testing)
- Lombok (optional, for reducing boilerplate code)
- Git (for version control)

## Project Structure

```bash
TaskManagementApp/
    ├── src/
      ├── main/
        ├── java/
          ├── Task.java
          ├── TaskManager.java
          └── TaskManagementApp.java
      ├── test/
        ├── java/
          └── TaskManagerTest.java
    ├── pom.xml
    └── README.md
```

## Setup Instructions

### Prerequisites

- Java 11 or higher
- Maven 3.x
- Git

### Steps to Setup the Project

1. **Clone the repository**:

```bash
git clone https://github.com/makrischaralampos/task-manager.git
cd task-management-app
```

2. **Build the project using Maven**: Maven will download dependencies and compile the project.

```bash
mvn clean install
```

3. **Run Unit Tests**: To run the unit tests and ensure everything is working:

```bash
mvn test
```

4. **Run the Application**: Run the main class `TaskManagementApp` from the command line:

```bash
mvn exec:java -Dexec.mainClass="com.example.TaskManagementApp"
```

## Usage

The Task Management App provides an interactive command-line interface where you can perform the following operations:

### Commands

- **Add Task**: Adds a new task to the task list.
- **View Tasks**: Displays all tasks.
- **Update Task**: Updates an existing task.
- **Delete Task**: Deletes a task by its ID.
- **Sort Tasks**: Sort tasks by priority or due date.
- **Filter Tasks**: Filter tasks based on priority or due date.

### Example CLI Interaction:

```bash
Welcome to the Task Management App!
Please choose an action:
1. Add Task
2. View All Tasks
3. Update Task
4. Delete Task
5. Sort Tasks by Priority
6. Sort Tasks by Due Date
7. Filter Tasks by High Priority
8. Filter Tasks Due This Week
9. Exit
```

## Key Features and Commands

1. **Add Task**

Allows users to create a task by providing:

- Title
- Description
- Due Date
- Priority
- Status

2. **View All Tasks**

Displays all tasks currently in memory, showing their title, description, due date, priority, and status.

3. **Update Task**

Updates the details of an existing task based on its ID.

4. **Delete Task**

Deletes a task from the list based on its ID.

5. **Sort Tasks**

- **Sort by Priority**: Lists tasks in order of priority (higher priority tasks first).
- **Sort by Due Date**: Lists tasks in order of their due date (earlier tasks first).

6. **Filter Tasks**

- **Filter by High Priority**: Displays only tasks marked with the highest priority.
- **Filter by Due Date**: Displays tasks due within the current week.

## Contributing

If you'd like to contribute to this project, feel free to submit issues or pull requests.

## License

This project is open-source under the MIT License. See the `License` file for details.