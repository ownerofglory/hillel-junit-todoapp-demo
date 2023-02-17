package ua.hillel.todolistdemo.ui.component;

import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import ua.hillel.todolistdemo.model.Todo;
import ua.hillel.todolistdemo.ui.event.TodoToggleEvent;

public class TodoListCellBoxFactory implements Callback<ListView<Todo>, ListCell<Todo>> {
    private EventHandler<TodoToggleEvent> handler;

    public void setHandler(EventHandler<TodoToggleEvent> handler) {
        this.handler = handler;
    }

    @Override
    public ListCell<Todo> call(ListView<Todo> todoListView) {
        return new ListCell<>() {
            @Override
            protected void updateItem(Todo todo, boolean empty) {
                super.updateItem(todo, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else if (todo != null) {
                    setText(null);
                    setMinHeight(100);
                    setPrefHeight(48);
                    setGraphic(new TodoListCellDelegate(todo, handler));
                } else {
                    setGraphic(null);
                    setText(null);
                }
            }
        };
    }
}
