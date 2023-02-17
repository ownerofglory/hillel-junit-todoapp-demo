package ua.hillel.todolistdemo.ui.component;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import ua.hillel.todolistdemo.model.TodoList;

import java.util.List;

public class TodoListListView extends ListView<TodoList> {
    public TodoListListView() {
        setCellFactory(new TodoListListCellBoxFactory());
        setHeight(600);
    }

    public void addTodoLists(List<TodoList> todoLists) {
        if (todoLists != null) {
            setItems(FXCollections.observableList(todoLists));
        }
    }
}
