package org.example.strategy.search;

import org.example.singleton.Event;

public class NameSearchStrategy implements SearchStrategy {
    public boolean matches(Event event, String keyword) {
        return event.getName().toLowerCase().contains(keyword.toLowerCase());
    }
}
