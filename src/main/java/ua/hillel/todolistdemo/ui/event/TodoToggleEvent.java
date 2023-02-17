package ua.hillel.todolistdemo.ui.event;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class TodoToggleEvent  extends Event  {
    public TodoToggleEvent(Object o, EventTarget eventTarget, EventType<? extends Event> eventType) {
        super(o, eventTarget, eventType);
    }
}
