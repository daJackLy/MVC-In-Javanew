package controllers;

import java.util.ArrayList;
import java.util.List;
import models.Event;

public class EventController {
    private List<Event> events;

    public EventController() {
        events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void removeEvent(int index) {
        if (index >= 0 && index < events.size()) {
            events.remove(index);
        }
    }
}

