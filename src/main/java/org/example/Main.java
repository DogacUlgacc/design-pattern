package org.example;

import org.example.command.ModifyCommand;
import org.example.command.UndoManager;
import org.example.command.UpdateListCommand;
import org.example.singleton.Event;
import org.example.singleton.EventManager;
import org.example.singleton.Field;
import org.example.strategy.search.CategorySearchStrategy;
import org.example.strategy.search.NameSearchStrategy;
import org.example.strategy.search.SearchStrategy;
import org.example.strategy.search.TagSearchStrategy;
import org.example.strategy.sort.SortByNameAsc;
import org.example.strategy.sort.SortByNameDesc;
import org.example.strategy.sort.SortStrategy;

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
            System.out.println("1. Create Event\n2. Search Event\n3. Register to Event\n4. Modify Event\n5. Undo\n6. Cancel Registration\n7. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> createEvent();
                case 2 -> searchEvent();
                case 3 -> registerEvent();
                case 4 -> modifyEvent();
                case 5 -> undoManager.undo();
                case 6 -> cancelRegistration();
                case 7 -> System.exit(0);
                default -> System.out.println("Invalid option");
            }
        }
    }

    private static void createEvent() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Location: ");
        String location = scanner.nextLine();
        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Time (HH:MM): ");
        String time = scanner.nextLine();
        System.out.print("Organizer: ");
        String organizer = scanner.nextLine();

        List<String> categories = new ArrayList<>();
        System.out.println("Enter up to three categories (press Enter after each category, or enter 'done' to finish):");
        for (int i = 0; i < 3; i++) {
            String cat = scanner.nextLine();
            if (cat.isEmpty()) break;
            categories.add(cat);
        }

        List<String> tags = new ArrayList<>();
        System.out.println("Enter up to three tags (press Enter after each tag, or enter 'done' to finish):");
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
        System.out.print("Search by (name/category/tag): ");
        String type = scanner.nextLine();
        SearchStrategy strategy = switch (type.toLowerCase()) {
            case "category" -> new CategorySearchStrategy();
            case "tag" -> new TagSearchStrategy();
            default -> new NameSearchStrategy();
        };

        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();

        List<Event> results = eventManager.search(keyword, strategy);
        if (results.isEmpty()) {
            System.out.println("No results found.");
            return;
        }

        System.out.print("Sort by name asc or desc? (a/d): ");
        boolean asc = scanner.nextLine().equalsIgnoreCase("a");
        SortStrategy sortStrategy = asc ? new SortByNameAsc() : new SortByNameDesc();
        sortStrategy.sort(results); //

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

    private static void cancelRegistration() {
        System.out.print("Enter event name to cancel registration: ");
        String name = scanner.nextLine();
        for (Event e : eventManager.getEvents()) {
            if (e.getName().equalsIgnoreCase(name)) {
                if (e.getRegistrationCount() > 0) {
                    e.cancel();
                    System.out.println("Your registration is canceled.");
                } else {
                    System.out.println("No registrations to cancel.");
                }
                return;
            }
        }
        System.out.println("Event not found.");
    }


    private static void modifyEvent() {
        System.out.print("Enter event name to modify: ");
        String name = scanner.nextLine();
        boolean found = false;

        for (Event e : eventManager.getEvents()) {
            if (e.getName().equalsIgnoreCase(name)) {
                found = true;

                System.out.print("Enter new location: ");
                String newLocation = scanner.nextLine();
                undoManager.execute(new ModifyCommand(e, Field.LOCATION, newLocation));

                System.out.print("Enter new date: ");
                String newDate = scanner.nextLine();
                undoManager.execute(new ModifyCommand(e, Field.DATE, newDate));

                System.out.print("Enter new time: ");
                String newTime = scanner.nextLine();
                undoManager.execute(new ModifyCommand(e, Field.TIME, newTime));

                System.out.println("Enter up to three new categories:");
                List<String> newCategories = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    String cat = scanner.nextLine();
                    if (cat.isEmpty()) break;
                    newCategories.add(cat);
                }
                undoManager.execute(new UpdateListCommand(e, Field.CATEGORIES, newCategories));

                System.out.println("Enter up to three new tags:");
                List<String> newTags = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    String tag = scanner.nextLine();
                    if (tag.isEmpty()) break;
                    newTags.add(tag);
                }
                undoManager.execute(new UpdateListCommand(e, Field.TAGS, newTags));


                System.out.println("Event updated.");
            }
        }

        if (!found) {
            System.out.println("Event not found.");
        }
    }

}
