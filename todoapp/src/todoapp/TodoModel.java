package todoapp;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class TodoModel {
    private Connection connection;

    public TodoModel() throws SQLException {
        // Establishing JDBC connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo_db", "root", "Chirag@123");

            // Creating table if not exists
            Statement statement = connection.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS tasks (id INT AUTO_INCREMENT PRIMARY KEY, task VARCHAR(255))";
            statement.executeUpdate(createTableQuery);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addTask(String taskName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO tasks (task) VALUES (?)");
        statement.setString(1, taskName);
        statement.executeUpdate();
    }

    public void updateTask(int taskId, String newTaskName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE tasks SET task = ? WHERE id = ?");
        statement.setString(1, newTaskName);
        statement.setInt(2, taskId);
        statement.executeUpdate();
    }

    public void deleteTask(int taskId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM tasks WHERE id = ?");
        statement.setInt(1, taskId);
        statement.executeUpdate();
    }

    public DefaultListModel<String> getAllTasks() throws SQLException {
        DefaultListModel<String> tasks = new DefaultListModel<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks");
        while (resultSet.next()) {
            tasks.addElement(resultSet.getString("task"));
        }
        return tasks;
    }


    public int getTaskId(int index) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM tasks LIMIT ?, 1");
        statement.setInt(1, index);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt("id");
    }
}
