package org.example.command;

import java.util.Stack;

public class UndoManager {
private Stack<Command> history = new Stack<>();

public void execute(Command command) {
    command.execute();
    history.push(command);
}

public void undo() {
    if (!history.isEmpty()) {
        history.pop().undo();
        System.out.println("The transaction was rolled back");
    } else {
        System.out.println("Nothing to undo.");
    }
}
}