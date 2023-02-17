package ua.hillel.todolistdemo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ua.hillel.todolistdemo.exception.TodoAppGeneralException;
import ua.hillel.todolistdemo.model.TodoList;
import ua.hillel.todolistdemo.repo.TodoListRepo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TodoListServiceImplTest {
    private TodoListService service;
    private TodoListRepo repoMock;

    @BeforeEach
    public void setUp() {
        repoMock = Mockito.mock(TodoListRepo.class);
        service = new TodoListServiceImpl(repoMock);
    }

    @Test
    public void addListTest_success() throws TodoAppGeneralException {
        doNothing().when(repoMock).add(any());

        service.addList("TEST");
    }

    @Test
    public void addListTest_throwsTodoAppGeneralException() throws TodoAppGeneralException {
        doThrow(new TodoAppGeneralException("", new Exception()))
                .when(repoMock)
                .add(any());

        assertThrows(TodoAppGeneralException.class, () ->
                service.addList("TEST"));
    }

    @Test
    public void getTodoListsTest_success() throws TodoAppGeneralException {
        List<TodoList> expected = new ArrayList<>() {{
            TodoList todoList = new TodoList();
            todoList.setName("");
            add(todoList);
        }};

        when(repoMock.getAll()).thenReturn(expected);

        List<TodoList> actual = service.getTodoLists();

        assertNotNull(actual);
        assertEquals(expected, actual);
        assertFalse(actual.isEmpty());
    }
}
