package ua.hillel.todolistdemo.repo;

import ua.hillel.todolistdemo.exception.TodoAppGeneralException;
import ua.hillel.todolistdemo.model.Todo;

import java.util.List;

public interface TodoRepo {
    void addTodo(Todo todo) throws TodoAppGeneralException;
    List<Todo> getTodosByListId(Integer listId) throws TodoAppGeneralException;
    void updateTodo(Todo todo) throws TodoAppGeneralException;
}
