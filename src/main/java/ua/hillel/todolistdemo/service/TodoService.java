package ua.hillel.todolistdemo.service;

import ua.hillel.todolistdemo.exception.TodoAppGeneralException;
import ua.hillel.todolistdemo.model.Todo;
import ua.hillel.todolistdemo.model.TodoList;

import java.util.List;

public interface TodoService {
    void addTodo(Todo todo) throws TodoAppGeneralException;
    List<Todo> getTodosByList(TodoList todoList) throws TodoAppGeneralException;
    void toggleTodoStatus(Todo todo) throws TodoAppGeneralException;
}
