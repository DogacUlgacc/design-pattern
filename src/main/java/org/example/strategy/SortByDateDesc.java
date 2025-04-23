package org.example.strategy;

import org.example.singleton.Event;

import java.util.List;

public class SortByDateDesc implements SortStrategy {
    public void sort(List<Event> events) {
        events.sort((e1, e2) -> e2.getDate().compareTo(e1.getDate()));
    }
}
