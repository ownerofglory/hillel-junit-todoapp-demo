package ua.hillel.todolistdemo.ui;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ua.hillel.todolistdemo.exception.TodoAppGeneralException;
import ua.hillel.todolistdemo.model.Todo;
import ua.hillel.todolistdemo.model.TodoList;
import ua.hillel.todolistdemo.service.TodoListService;
import ua.hillel.todolistdemo.service.TodoService;
import ua.hillel.todolistdemo.ui.component.TodoListListView;
import ua.hillel.todolistdemo.ui.component.TodoListView;
import ua.hillel.todolistdemo.ui.event.TodoToggleEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainUiController implements Initializable {
    @FXML
    private Label listNameLabel;
    @FXML
    private TodoListListView todoListView;
    @FXML
    private TodoListView todoView;
    @FXML
    private TextField listNameInput;
    @FXML
    private TextField todoInput;
    @FXML
    private Button addListButton;
    @FXML
    private Button addTodoButton;

    private final TodoListService todoListService;
    private final TodoService todoService;

    private TodoList activeList;

    public MainUiController(TodoListService todoListService, TodoService todoService) {
        this.todoListService = todoListService;
        this.todoService = todoService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // get all lists
            List<TodoList> todoLists = todoListService.getTodoLists();
            todoListView.addTodoLists(todoLists);

            if (todoLists != null && todoLists.size() > 0) {
                if (activeList == null) {
                    activeList = todoLists.get(0);
                    listNameLabel.setText(activeList.getName());
                }
                // open all tasks for the fists list
                List<Todo> todos = todoService.getTodosByList(activeList);
                todoView.addTodos(todos);
            }

            todoView.addOnTodoToggleHandler(this::onActiveTodoChanged);
            addListButton.setOnMouseClicked(this::onAddListButtonClick);
            addTodoButton.setOnMouseClicked(this::onAddTodoButtonClick);
            todoListView.getSelectionModel()
                    .selectedIndexProperty()
                    .addListener(this::onActiveListChanged);
        } catch (TodoAppGeneralException e) {
            showModal(e.getMessage());
        }
    }

    private void onAddListButtonClick(MouseEvent event) {
        try {
            String listName = this.listNameInput.getText();
            if (listName != null && listName.length() != 0) {
                todoListService.addList(listName);
                this.listNameInput.setText("");
            }
            List<TodoList> todoLists = todoListService.getTodoLists();
            todoListView.addTodoLists(todoLists);
            if (activeList == null) {
                activeList = todoLists.get(0);
                listNameLabel.setText(activeList.getName());
                List<Todo> todos = todoService.getTodosByList(activeList);
                todoView.addTodos(todos);
            }
        } catch (TodoAppGeneralException e) {
            showModal(e.getMessage());
        }
    }

    private void onAddTodoButtonClick(MouseEvent event) {
        try {
            String todoTitle = this.todoInput.getText();
            if (todoTitle != null && todoTitle.length() != 0) {
                Todo todo = new Todo();
                todo.setTitle(todoTitle);
                todo.setStatus(false);
                todo.setTodoList(activeList);
                todoService.addTodo(todo);
                this.todoInput.setText("");
            }
            List<Todo> todosByList = todoService.getTodosByList(activeList);
            todoView.addTodos(todosByList);
        } catch (TodoAppGeneralException e) {
            showModal(e.getMessage());
        }
    }

    private void onActiveListChanged(ObservableValue<? extends Number> e, Number oldValue, Number newValue) {
        try {
            activeList =  todoListView.getItems().get((int) newValue);
            listNameLabel.setText(activeList.getName());
            List<Todo> todosByList = todoService.getTodosByList(activeList);
            todoView.addTodos(todosByList);
        } catch (TodoAppGeneralException ex) {
            showModal(ex.getMessage());
        }
    }

    private void onActiveTodoChanged(TodoToggleEvent e) {
        try {
            todoService.toggleTodoStatus((Todo) e.getSource());
            List<Todo> todosByList = todoService.getTodosByList(activeList);
            todoView.addTodos(todosByList);
        } catch (TodoAppGeneralException ex) {
            showModal(ex.getMessage());
        }
    }

    private void showModal(String message) {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(40));
        vBox.setMaxSize(200, 100);
        Label label = new Label(message);
        label.setFont(new Font(18));
        vBox.getChildren().add(label);
        Scene scene = new Scene(vBox);
        Stage modalStage = new Stage();
        modalStage.setScene(scene);
        modalStage.setTitle("ToDo App");
        modalStage.initModality(Modality.WINDOW_MODAL);
        modalStage.showAndWait();
    }
}
