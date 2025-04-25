package org.example.strategy.search;

import org.example.singleton.Event;

public class CategorySearchStrategy implements SearchStrategy {
    public boolean matches(Event event, String keyword) {
        return event.getCategories().stream()
                .anyMatch(c -> c.toLowerCase().contains(keyword.toLowerCase()));
    }
}
