package todoapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TodoView extends JFrame {
    private JTextField taskField;
    private DefaultListModel<String> todoListModel;
    private JList<String> todoList;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton saveButton;

    public TodoView() {
        setTitle("To-Do List");
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Panel for "Welcome to application" label
        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome to my application");
        welcomePanel.add(welcomeLabel);

        // Panel for task input
        JPanel inputPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("What to do:");
        taskField = new JTextField(20);
        inputPanel.add(label, BorderLayout.WEST);
        inputPanel.add(taskField, BorderLayout.CENTER);

        // Panel for buttons
        saveButton = new JButton("Save");
        JPanel buttonPanel = new JPanel();
        editButton = new JButton("Edit ");
        deleteButton = new JButton("Done");
        buttonPanel.add(saveButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        

        // Panel for task list
        todoListModel = new DefaultListModel<>();
        todoList = new JList<>(todoListModel);

        JScrollPane scrollPane = new JScrollPane(todoList);

        // Add panels to frame
        setLayout(new BorderLayout());
        add(welcomePanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public String getTaskName() {
        return taskField.getText().trim();
    }

    public void setTaskName(String taskName) {
        taskField.setText(taskName);
    }

    public void setAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void setEditButtonListener(ActionListener listener) {
        editButton.addActionListener(listener);
    }

    public void setDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void setSaveButtonListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    public int getSelectedIndex() {
        return todoList.getSelectedIndex();
    }

    public void setListData(DefaultListModel<String> model) {
        todoList.setModel(model);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
