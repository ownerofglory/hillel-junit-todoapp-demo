package ua.hillel.todolistdemo.ui.component;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import ua.hillel.todolistdemo.model.Todo;
import ua.hillel.todolistdemo.ui.event.TodoToggleEvent;

public class TodoListCellDelegate extends HBox {
    public TodoListCellDelegate(Todo todo, EventHandler<TodoToggleEvent> handler) {
        setPadding(new Insets(8));
        Label label = new Label(todo.getTitle());
        getChildren().add(new GridPane() {{
            label.setFont(new Font(16));
            label.setMinWidth(720);

            CheckBox checkBox = new CheckBox();
            checkBox.setSelected(todo.getStatus());
            checkBox.selectedProperty().addListener((e, oldV, newV) -> {
                TodoToggleEvent todoToggleEvent = new TodoToggleEvent(todo, this, MouseEvent.ANY);
                handler.handle(todoToggleEvent);
            });

            add(label, 0, 1);
            add(checkBox, 0, 1);

            setColumnIndex(label, 0);
            setColumnIndex(checkBox, 1);
        }});

        setMargin(this, new Insets(12));
    }
}
