# Hillel JUnit ToDo-List Demo

## Build

### Prerequisites
- JDK v11
- [JavaFX SDK v19](https://openjfx.io/openjfx-docs/#install-javafx)
- Maven

### Build config
- VM options 
```sh
--module-path /Users/YourUser/$javaFXSDKdir/lib --add-modules javafx.controls,javafx.fxml
```
- Environment vars
```sh
    SQLITE_CONN_STRING=jdbc:sqlite:/Users/YourUser/$DBdir/todo-app.db
```
- execute build
```sh
    mvn clean package
```

### Run
```sh
    mvn -DsqliteConnString=${sqliteConnString} javafx:run
```

