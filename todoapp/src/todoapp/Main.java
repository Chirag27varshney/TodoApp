package todoapp;

import java.sql.SQLException;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TodoView view = new TodoView();
                TodoModel model = new TodoModel();
                @SuppressWarnings("unused")
				TodoController controller = new TodoController(view, model);
                view.setVisible(true);
            } catch (SQLException e) {
            }
        });
    }
}
