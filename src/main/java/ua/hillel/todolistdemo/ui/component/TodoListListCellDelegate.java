package ua.hillel.todolistdemo.ui.component;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import ua.hillel.todolistdemo.model.TodoList;

import java.awt.*;

public class TodoListListCellDelegate extends HBox {
    public TodoListListCellDelegate(TodoList todoList) {
        setPadding(new Insets(12));
        Label label = new Label(todoList.getName());
        label.setFont(new Font(16));
        this.getChildren().add(label);
    }
}
