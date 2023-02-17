package ua.hillel.todolistdemo.exception;

public class DbInitException extends Exception{
    public DbInitException(String message, Throwable cause) {
        super(message, cause);
    }
}
