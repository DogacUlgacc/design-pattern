package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static EventManager eventManager = EventManager.getInstance();
    static UndoManager undoManager = new UndoManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter command: ");
            System.out.println("1. Create Event\n2. Search Event\n3. Register to Event\n4. Modify Event\n5. Undo\n6. Exit");
            int choice = scanner.nextInt(); scanner.nextLine();
            switch (choice) {
                case 1 -> createEvent();
                case 2 -> searchEvent();
                case 3 -> registerEvent();
                case 4 -> modifyEvent();
                case 5 -> undoManager.undo();
                case 6 -> System.exit(0);
                default -> System.out.println("Invalid option");
            }
        }
    }

    private static void createEvent() {
        System.out.print("Name: "); String name = scanner.nextLine();
        System.out.print("Location: "); String location = scanner.nextLine();
        System.out.print("Date (YYYY-MM-DD): "); String date = scanner.nextLine();
        System.out.print("Time (HH:MM): "); String time = scanner.nextLine();
        System.out.print("Organizer: "); String organizer = scanner.nextLine();

        List<String> categories = new ArrayList<>();
        System.out.println("Enter up to 3 categories:");
        for (int i = 0; i < 3; i++) {
            String cat = scanner.nextLine();
            if (cat.isEmpty()) break;
            categories.add(cat);
        }

        List<String> tags = new ArrayList<>();
        System.out.println("Enter up to 3 tags:");
        for (int i = 0; i < 3; i++) {
            String tag = scanner.nextLine();
            if (tag.isEmpty()) break;
            tags.add(tag);
        }

        Event e = new Event(name, location, date, time, organizer, categories, tags);
        eventManager.addEvent(e);
        System.out.println("Event saved.");
    }

    private static void searchEvent() {
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();
        List<Event> results = eventManager.search(keyword);
        if (results.isEmpty()) {
            System.out.println("No results found.");
            return;
        }
        System.out.print("Sort by name ascending? (y/n): ");
        boolean asc = scanner.nextLine().equalsIgnoreCase("y");
        eventManager.sortByName(asc);
        for (int i = 0; i < results.size(); i++) {
            System.out.println(i + ": " + results.get(i));
        }
    }

    private static void registerEvent() {
        System.out.print("Enter event name to register: ");
        String name = scanner.nextLine();
        for (Event e : eventManager.getEvents()) {
            if (e.getName().equalsIgnoreCase(name)) {
                e.register();
                System.out.println("You are registered.");
                return;
            }
        }
        System.out.println("Event not found.");
    }

    private static void modifyEvent() {
        System.out.print("Enter event name to modify: ");
        String name = scanner.nextLine();
        for (Event e : eventManager.getEvents()) {
            if (e.getName().equalsIgnoreCase(name)) {
                System.out.print("Enter new location: ");
                String newLocation = scanner.nextLine();
                ModifyCommand cmd = new ModifyCommand(e, newLocation);
                undoManager.execute(cmd);


            }
        }
        System.out.println("Event not found.");
    }
}
