class Book {
    String title, author, genre;
    int bookId;
    boolean isAvailable;
    Book next, prev;

    public Book(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
    }
}

class Library {
    Book head = null, tail = null;

    void addBookAtBeginning(Book book) {
        if (head == null) {
            head = tail = book;
        } else {
            book.next = head;
            head.prev = book;
            head = book;
        }
    }

    void addBookAtEnd(Book book) {
        if (head == null) {
            head = tail = book;
        } else {
            tail.next = book;
            book.prev = tail;
            tail = book;
        }
    }

    void addBookAtPosition(Book book, int position) {
        if (position == 0) {
            addBookAtBeginning(book);
            return;
        }
        Book temp = head;
        for (int i = 0; i < position - 1 && temp != null; i++) temp = temp.next;
        if (temp == null || temp.next == null) {
            addBookAtEnd(book);
        } else {
            book.next = temp.next;
            book.prev = temp;
            temp.next.prev = book;
            temp.next = book;
        }
    }

    void removeBookById(int id) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == id) {
                if (temp == head) head = temp.next;
                if (temp == tail) tail = temp.prev;
                if (temp.prev != null) temp.prev.next = temp.next;
                if (temp.next != null) temp.next.prev = temp.prev;
                return;
            }
            temp = temp.next;
        }
    }

    void updateAvailability(int id, boolean status) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == id) {
                temp.isAvailable = status;
                return;
            }
            temp = temp.next;
        }
    }

    void searchBook(String keyword) {
        Book temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(keyword) || temp.author.equalsIgnoreCase(keyword)) {
                System.out.println(temp.title + " | ID: " + temp.bookId + " | Available: " + temp.isAvailable);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    void displayForward() {
        Book temp = head;
        while (temp != null) {
            System.out.println(temp.title + " | Author: " + temp.author + " | ID: " + temp.bookId + " | Available: " + temp.isAvailable);
            temp = temp.next;
        }
    }

    void displayReverse() {
        Book temp = tail;
        while (temp != null) {
            System.out.println(temp.title + " | Author: " + temp.author + " | ID: " + temp.bookId + " | Available: " + temp.isAvailable);
            temp = temp.prev;
        }
    }

    void countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println("Total Books: " + count);
    }
}

public class LibraryMain {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBookAtEnd(new Book("Java Programming", "James Gosling", "Education", 1, true));
        library.addBookAtBeginning(new Book("1984", "George Orwell", "Fiction", 2, true));
        library.addBookAtEnd(new Book("Python Basics", "Guido", "Education", 3, false));
        library.addBookAtPosition(new Book("C++ Guide", "Bjarne Stroustrup", "Education", 4, true), 2);

        System.out.println("Books in Library:");
        library.displayForward();

        library.removeBookById(3);
        System.out.println("\nAfter Removing Book ID 3:");
        library.displayForward();

        library.searchBook("1984");
        library.updateAvailability(1, false);

        System.out.println("\nUpdated Availability:");
        library.displayForward();

        System.out.println("\nReverse Order:");
        library.displayReverse();

        library.countBooks();
    }
}




