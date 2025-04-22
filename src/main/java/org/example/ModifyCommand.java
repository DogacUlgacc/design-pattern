package org.example;

//Command Design pattern implementation
class ModifyCommand implements Command {
    private Event event;
    private String oldLocation, newLocation;
    private String oldDate, newDate;
    private String oldTime, newTime;


    public ModifyCommand(Event event, String newLocation) {
        this.event = event;
        this.oldLocation = event.getLocation();
        this.newLocation = newLocation;
    }

    public void execute() {
        event.setLocation(newLocation);
        event.setDate(newDate);
        event.setTime(newTime);
    }

    public void undo() {
        event.setLocation(oldLocation);
        event.setTime(oldTime);
        event.setDate(oldDate);
    }
}