package ua.hillel.todolistdemo.service;

import ua.hillel.todolistdemo.exception.TodoAppGeneralException;
import ua.hillel.todolistdemo.model.Todo;
import ua.hillel.todolistdemo.model.TodoList;

import java.util.List;

public interface TodoListService {
    void addList(String name) throws TodoAppGeneralException;
    List<TodoList> getTodoLists() throws TodoAppGeneralException;
}
