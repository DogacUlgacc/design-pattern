package org.example.strategy.search;
import org.example.singleton.Event;

public class TagSearchStrategy implements SearchStrategy {
    public boolean matches(Event event, String keyword) {
        return event.getTags().stream()
                .anyMatch(t -> t.toLowerCase().contains(keyword.toLowerCase()));
    }
}