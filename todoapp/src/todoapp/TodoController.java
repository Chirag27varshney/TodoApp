package todoapp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TodoController {
    private TodoView view;
    private TodoModel model;

    public TodoController(TodoView view, TodoModel model) {
        this.view = view;
        this.model = model;
        this.view.setEditButtonListener(new EditButtonListener());
        this.view.setDeleteButtonListener(new DeleteButtonListener());
        this.view.setSaveButtonListener(new SaveButtonListener());

        try {
            this.view.setListData(this.model.getAllTasks());
        } catch (SQLException e) {
            view.showMessage("Error fetching tasks: " + e.getMessage());
        }
    }

    class EditButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = view.getSelectedIndex();
            if (selectedIndex != -1) {
                String newTaskName = view.getTaskName();
                if (!newTaskName.isEmpty()) {
                    try {
                        int taskId = model.getTaskId(selectedIndex);
                        model.updateTask(taskId, newTaskName); // Update in database
                        view.setListData(model.getAllTasks()); // Refresh UI
                    } catch (SQLException ex) {
                        view.showMessage("Error updating task: " + ex.getMessage());
                    }
                }
            }
        }
    }

    class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = view.getSelectedIndex();
            if (selectedIndex != -1) {
                try {
                    int taskId = model.getTaskId(selectedIndex);
                    model.deleteTask(taskId); // Delete from database
                    view.setListData(model.getAllTasks()); // Refresh UI
                } catch (SQLException ex) {
                    view.showMessage("Error deleting task: " + ex.getMessage());
                }
            }
        }
    }


    class SaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String taskName = view.getTaskName();
            if (!taskName.isEmpty()) {
                try {
                    model.addTask(taskName);
                    view.setListData(model.getAllTasks());
                    view.setTaskName("");
                } catch (SQLException ex) {
                    view.showMessage("Error saving task: " + ex.getMessage());
                }
            }
        }
    }
}
