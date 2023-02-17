module ua.hillel.todolistdemo {
    requires javafx.fxml;
    requires javafx.controls;
    opens ua.hillel.todolistdemo to javafx.graphics;
    exports ua.hillel.todolistdemo;
}
