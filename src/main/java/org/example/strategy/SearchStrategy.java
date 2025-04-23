package org.example.strategy;

import org.example.singleton.Event;

public interface SearchStrategy {
    boolean matches(Event event, String keyword);
}