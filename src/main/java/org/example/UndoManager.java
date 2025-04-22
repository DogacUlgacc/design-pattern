package org.example;

import java.util.Stack;

class UndoManager {
private Stack<Command> history = new Stack<>();

public void execute(Command command) {
    command.execute();
    history.push(command);
}

public void undo() {
    if (!history.isEmpty()) {
        history.pop().undo();
    } else {
        System.out.println("Nothing to undo.");
    }
}
}