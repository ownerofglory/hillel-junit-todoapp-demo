package ua.hillel.todolistdemo.service;

import ua.hillel.todolistdemo.exception.TodoAppGeneralException;
import ua.hillel.todolistdemo.model.TodoList;
import ua.hillel.todolistdemo.repo.TodoListRepo;

import java.util.List;

public class TodoListServiceImpl implements TodoListService {
    private final TodoListRepo repo;

    public TodoListServiceImpl(TodoListRepo repo) {
        this.repo = repo;
    }

    @Override
    public void addList(String name) throws TodoAppGeneralException {
        TodoList todoList = new TodoList();
        todoList.setName(name);

        repo.add(todoList);
    }

    @Override
    public List<TodoList> getTodoLists() throws TodoAppGeneralException {
        return repo.getAll();
    }
}
