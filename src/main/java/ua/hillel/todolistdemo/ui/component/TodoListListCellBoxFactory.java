package ua.hillel.todolistdemo.ui.component;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import ua.hillel.todolistdemo.model.TodoList;

public class TodoListListCellBoxFactory implements Callback<ListView<TodoList>, ListCell<TodoList>> {
    @Override
    public ListCell<TodoList> call(ListView<TodoList> todoListListView) {
        return new ListCell<>() {
            @Override
            protected void updateItem(TodoList todoList, boolean empty) {
                super.updateItem(todoList, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else if (todoList != null) {
                    setText(null);
                    setPrefHeight(48);
                    setGraphic(new TodoListListCellDelegate(todoList));
                    setHeight(100);
                } else {
                    setGraphic(null);
                    setText(null);
                }
            }
        };
    }
}
