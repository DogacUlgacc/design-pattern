package org.example;
import java.util.*;

public class Event {
    private String name;
    private String location;
    private String date;
    private String time;
    private final String organizer;
    private List<String> categories = new ArrayList<>();
    private List<String> tags = new ArrayList<>();
    private int registrationCount = 0;

    public Event(String name, String location, String date, String time, String organizer,
                 List<String> categories, List<String> tags) {
        this.name = name;
        this.location = location;
        this.date = date;
        this.time = time;
        this.organizer = organizer;
        this.categories = categories;
        this.tags = tags;
    }

    public String getName() { return name; }
    public void setLocation(String location) { this.location = location; }
    public void setDate(String date) { this.date = date; }
    public void setTime(String time) { this.time = time; }
    public String getLocation() { return location; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public List<String> getCategories() { return categories; }
    public List<String> getTags() { return tags; }
    public int getRegistrationCount() { return registrationCount; }
    public void register() { registrationCount++; }
    public void cancel() { if (registrationCount > 0) registrationCount--; }

    public String toString() {
        return "Event: " + name + " | " + location + " | " + date + " | " + time +
                " | Organizer: " + organizer + " | Categories: " + categories + " | Tags: " + tags +
                " | Registrations: " + registrationCount;
    }
}