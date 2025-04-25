package org.example.strategy.sort;

import org.example.singleton.Event;
import java.util.List;

public interface SortStrategy {
    void sort(List<Event> events);
}
