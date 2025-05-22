class TextState {
    String content;
    TextState prev, next;

    public TextState(String content) {
        this.content = content;
    }
}

class TextEditor {
    TextState head = null, current = null;
    int size = 0;
    final int MAX_HISTORY = 10;

    void addState(String content) {
        TextState newState = new TextState(content);
        if (head == null) {
            head = current = newState;
        } else {
            // Clear redo history
            current.next = null;
            newState.prev = current;
            current.next = newState;
            current = newState;
        }

        // Maintain max history size
        size++;
        if (size > MAX_HISTORY) {
            head = head.next;
            head.prev = null;
            size--;
        }
    }

    void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Undo: " + current.content);
        } else {
            System.out.println("No more undo steps available.");
        }
    }

    void redo() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Redo: " + current.content);
        } else {
            System.out.println("No more redo steps available.");
        }
    }

    void displayCurrentState() {
        if (current != null) {
            System.out.println("Current State: " + current.content);
        } else {
            System.out.println("No state available.");
        }
    }
}

public class TextEditorMain {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.addState("Hello");
        editor.addState("Hello World");
        editor.addState("Hello World!");

        editor.displayCurrentState();

        editor.undo();
        editor.undo();
        editor.redo();
        editor.addState("New Line Added");

        editor.displayCurrentState();
    }
}






