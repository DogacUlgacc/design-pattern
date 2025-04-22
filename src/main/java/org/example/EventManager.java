package org.example;

import java.util.ArrayList;
import java.util.List;

//(Singleton Design Pattern)
class EventManager {
    private static EventManager instance;
    private List<Event> events = new ArrayList<>();

    private EventManager() {}

    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public void addEvent(Event e) { events.add(e); }

    public List<Event> getEvents() { return events; }

    public List<Event> search(String keyword) {
        List<Event> result = new ArrayList<>();
        for (Event e : events) {
            if (e.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                    e.getTags().stream().anyMatch(tag -> tag.toLowerCase().contains(keyword.toLowerCase())) ||
                    e.getCategories().stream().anyMatch(cat -> cat.toLowerCase().contains(keyword.toLowerCase())) ||
                    e.getDate().contains(keyword)) {
                result.add(e);
            }
        }
        return result;
    }

    public void sortByName(boolean ascending) {
        events.sort((e1, e2) -> ascending ? e1.getName().compareTo(e2.getName()) : e2.getName().compareTo(e1.getName()));
    }
}