package ua.hillel.todolistdemo.repo;

import ua.hillel.todolistdemo.exception.TodoAppGeneralException;
import ua.hillel.todolistdemo.model.Todo;
import ua.hillel.todolistdemo.model.TodoList;

import java.util.List;

public interface TodoListRepo {
    List<TodoList> getAll() throws TodoAppGeneralException;
    void add(TodoList todoList) throws TodoAppGeneralException;
}
