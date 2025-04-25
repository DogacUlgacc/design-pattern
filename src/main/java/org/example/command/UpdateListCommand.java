package org.example.command;

import org.example.singleton.Event;
import org.example.singleton.Field;

import java.util.ArrayList;
import java.util.List;

public class UpdateListCommand implements Command {
    private final Event event;
    private final Field field;
    private final List<String> oldList;
    private final List<String> newList;

    public UpdateListCommand(Event event, Field field, List<String> newList) {
        this.event = event;
        this.field = field;
        this.oldList = new ArrayList<>(field == Field.CATEGORIES ? event.getCategories() : event.getTags());
        this.newList = newList;
    }

    @Override
    public void execute() {
        if (field == Field.CATEGORIES) {
            event.getCategories().clear();
            event.getCategories().addAll(newList);
        } else if (field == Field.TAGS) {
            event.getTags().clear();
            event.getTags().addAll(newList);
        }
    }

    @Override
    public void undo() {
        if (field == Field.CATEGORIES) {
            event.getCategories().clear();
            event.getCategories().addAll(oldList);
        } else if (field == Field.TAGS) {
            event.getTags().clear();
            event.getTags().addAll(oldList);
        }
    }
}
