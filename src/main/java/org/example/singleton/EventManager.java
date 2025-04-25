package org.example.singleton;

import org.example.strategy.search.SearchStrategy;
import org.example.strategy.sort.SortStrategy;

import java.util.ArrayList;
import java.util.List;

//(Singleton Design Pattern)
public class EventManager {
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

    public List<Event> search(String keyword, SearchStrategy strategy) {
        List<Event> result = new ArrayList<>();
        for (Event e : events) {
            if (strategy.matches(e, keyword)) {
                result.add(e);
            }
        }
        return result;
    }


    public void sortByName(boolean ascending) {
        events.sort((e1, e2) -> ascending ? e1.getName().compareTo(e2.getName()) : e2.getName().compareTo(e1.getName()));
    }
    public void sort(SortStrategy strategy) {
        strategy.sort(events);
    }

}