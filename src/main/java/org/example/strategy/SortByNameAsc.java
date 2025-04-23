package org.example.strategy;

import org.example.singleton.Event;
import java.util.Comparator;
import java.util.List;

public class SortByNameAsc implements SortStrategy {
    public void sort(List<Event> events) {
        events.sort(Comparator.comparing(Event::getName));
    }
}
