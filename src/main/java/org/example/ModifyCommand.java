package org.example;

//Command Design pattern implementation
public class ModifyCommand implements Command {
    private Event event;
    private Field field;
    private String oldValue;
    private String newValue;

    public ModifyCommand(Event event, Field field, String newValue) {
        this.event = event;
        this.field = field;
        this.newValue = newValue;

        // Değişiklik öncesi değer saklanıyor
        switch (field) {
            case LOCATION -> this.oldValue = event.getLocation();
            case DATE -> this.oldValue = event.getDate();
            case TIME -> this.oldValue = event.getTime();
        }
    }

    @Override
    public void execute() {
        switch (field) {
            case LOCATION -> event.setLocation(newValue);
            case DATE -> event.setDate(newValue);
            case TIME -> event.setTime(newValue);
        }
    }

    @Override
    public void undo() {
        switch (field) {
            case LOCATION -> event.setLocation(oldValue);
            case DATE -> event.setDate(oldValue);
            case TIME -> event.setTime(oldValue);
        }
    }
}
