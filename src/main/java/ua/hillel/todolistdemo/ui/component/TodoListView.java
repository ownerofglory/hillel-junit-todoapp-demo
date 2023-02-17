package ua.hillel.todolistdemo.ui.component;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import ua.hillel.todolistdemo.model.Todo;
import ua.hillel.todolistdemo.ui.event.TodoToggleEvent;

import java.util.List;

public class TodoListView extends ListView<Todo> {
    public TodoListView() {
        setCellFactory(new TodoListCellBoxFactory());
        setHeight(600);
    }
    public void addTodos(List<Todo> todos) {
        setItems(FXCollections.observableList(todos));
    }

    public void addOnTodoToggleHandler(EventHandler<TodoToggleEvent> handler) {
        Callback<ListView<Todo>, ListCell<Todo>> cellFactory = this.getCellFactory();
        if (cellFactory instanceof TodoListCellBoxFactory) {
            TodoListCellBoxFactory todoListCellBoxFactory = (TodoListCellBoxFactory) cellFactory;
            todoListCellBoxFactory.setHandler(handler);
        }
    }
}
