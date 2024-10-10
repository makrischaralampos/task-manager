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

1. **Clone the repository**

To get started, clone the repository to you local machine using Git. Open your terminal and run the following command:

```bash
git clone https://github.com/makrischaralampos/task-manager.git
```

Navigate into the project directory:

```bash
cd task-management-app
```

2. **Build the Project with Maven**

Once inside the project directory, build the project using Maven.
This will download the required dependencies (e.g., JUnit, Lombok) and compile the Java code.

To clean and build the project, run the following command:

```bash
mvn clean install
```

This command will:

- Clean the previous build (if any).
- Download dependencies specified in `pom.xml`.
- Compile the project code.

If the build is successful, Maven will display a `BUILD SUCCESS` message.

3. **Run Unit Tests (Optional)**

Before running the application, you can verify that everything is functioning correctly by running the unit tests:

```bash
mvn test
```

This will run all the JUnit tests to ensure the core operations
(add, update, delete, sort, filter tasks) work as expected.

4. **Run the Application**

After building the project, you can run the application using Maven. 
The app's entry point is the `TaskManagementApp` class. To execute the app, use the following command:

```bash
mvn exec:java -Dexec.mainClass="com.example.TaskManagementApp"
```

**Example Commands**

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

5. **Commit and Push Changes (Optional)**

If you make changes to the project and want to push them to your remote Git repository,
you can commit and push the changes:

```bash
git add .
git commit -m "Your commit message here"
git push origin main
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