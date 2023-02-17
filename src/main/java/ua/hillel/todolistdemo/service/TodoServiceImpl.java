package ua.hillel.todolistdemo.service;

import ua.hillel.todolistdemo.exception.TodoAppGeneralException;
import ua.hillel.todolistdemo.model.Todo;
import ua.hillel.todolistdemo.model.TodoList;
import ua.hillel.todolistdemo.repo.TodoRepo;

import java.util.List;
import java.util.stream.Collectors;

public class TodoServiceImpl implements TodoService {
    private final TodoRepo repo;

    public TodoServiceImpl(TodoRepo repo) {
        this.repo = repo;
    }

    @Override
    public void addTodo(Todo todo) throws TodoAppGeneralException {
        repo.addTodo(todo);
    }

    @Override
    public List<Todo> getTodosByList(TodoList todoList) throws TodoAppGeneralException {
        List<Todo> todos = repo.getTodosByListId(todoList.getId());
        return todos;
    }

    @Override
    public void toggleTodoStatus(Todo todo) throws TodoAppGeneralException {
        todo.setStatus(!todo.getStatus());
        repo.updateTodo(todo);
    }
}
