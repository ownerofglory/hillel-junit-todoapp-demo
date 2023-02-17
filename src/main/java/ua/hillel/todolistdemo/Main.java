package ua.hillel.todolistdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ua.hillel.todolistdemo.db.DbSchemaInitializer;
import ua.hillel.todolistdemo.repo.TodoListRepo;
import ua.hillel.todolistdemo.repo.TodoListSqliteJdbcRepo;
import ua.hillel.todolistdemo.repo.TodoRepo;
import ua.hillel.todolistdemo.repo.TodoSqliteJdbcRepo;
import ua.hillel.todolistdemo.service.TodoListService;
import ua.hillel.todolistdemo.service.TodoListServiceImpl;
import ua.hillel.todolistdemo.service.TodoService;
import ua.hillel.todolistdemo.service.TodoServiceImpl;
import ua.hillel.todolistdemo.ui.MainUiController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {
    private static Connection connection;

    public static void main(String[] args) {
        String sqliteConnStringEnv = System.getenv("SQLITE_CONN_STRING");
        String sqliteConnString = sqliteConnStringEnv != null
                ? sqliteConnStringEnv : System.getProperty("sqliteConnString");

        if (sqliteConnString == null) {
            sqliteConnString = "jdbc:sqlite:todo-app.db";
        }

        try (Connection conn = DriverManager.getConnection(sqliteConnString)) {
            connection = conn;

            launch(args);
        } catch (SQLException e) {
            System.out.println("Unable to connect to DB");
            System.out.printf("%s:%s\n", e.getMessage(), e.getCause());
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Class.forName("org.sqlite.JDBC");

        DbSchemaInitializer.init(connection, "todos-schema.sql");

        TodoRepo todoRepo = new TodoSqliteJdbcRepo(connection);
        TodoListRepo todoListRepo = new TodoListSqliteJdbcRepo(connection);

        TodoService todoService = new TodoServiceImpl(todoRepo);
        TodoListService todoListService = new TodoListServiceImpl(todoListRepo);

        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(param -> new MainUiController(todoListService, todoService));
        loader.setLocation(getClass().getResource("/scenes/lists-scenes.fxml"));
        VBox vBox = loader.<VBox>load();

        Scene listScene = new Scene(vBox);
        listScene.getStylesheets().add("/styles/lists.css");
        stage.setScene(listScene);
        stage.setTitle("Todo app");
        stage.show();
    }
}
