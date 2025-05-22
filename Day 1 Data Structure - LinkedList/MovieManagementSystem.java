import java.util.Scanner;

class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie prev;
    Movie next;

    public Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.prev = null;
        this.next = null;
    }
}

class MovieDoublyLinkedList {
    private Movie head;
    private Movie tail;

    // Add at beginning
    public void addAtBeginning(Movie newMovie) {
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    // Add at end
    public void addAtEnd(Movie newMovie) {
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    // Add at specific position (1-based)
    public void addAtPosition(Movie newMovie, int position) {
        if (position <= 1) {
            addAtBeginning(newMovie);
            return;
        }

        Movie current = head;
        int count = 1;
        while (current != null && count < position - 1) {
            current = current.next;
            count++;
        }

        if (current == null || current.next == null) {
            addAtEnd(newMovie);
        } else {
            newMovie.next = current.next;
            newMovie.prev = current;
            current.next.prev = newMovie;
            current.next = newMovie;
        }
    }

    // Remove by title
    public void removeByTitle(String title) {
        Movie current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                if (current == head) {
                    head = head.next;
                    if (head != null) head.prev = null;
                    else tail = null;
                } else if (current == tail) {
                    tail = tail.prev;
                    if (tail != null) tail.next = null;
                    else head = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                System.out.println("Movie \"" + title + "\" removed.");
                return;
            }
            current = current.next;
        }
        System.out.println("Movie \"" + title + "\" not found.");
    }

    // Search by director or rating
    public void searchByDirectorOrRating(String director, Double rating) {
        Movie current = head;
        boolean found = false;
        while (current != null) {
            if ((director != null && current.director.equalsIgnoreCase(director)) ||
                (rating != null && current.rating == rating)) {
                System.out.println("Title: " + current.title + ", Director: " + current.director +
                        ", Year: " + current.year + ", Rating: " + current.rating);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No matching movie found.");
        }
    }

    // Update rating by title
    public boolean updateRating(String title, double newRating) {
        Movie current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                current.rating = newRating;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Display all movies forward
    public void displayForward() {
        Movie current = head;
        if (current == null) {
            System.out.println("No movies in the list.");
            return;
        }
        System.out.println("Movies (Forward):");
        while (current != null) {
            System.out.println("Title: " + current.title + ", Director: " + current.director +
                    ", Year: " + current.year + ", Rating: " + current.rating);
            current = current.next;
        }
    }

    // Display all movies in reverse
    public void displayReverse() {
        Movie current = tail;
        if (current == null) {
            System.out.println("No movies in the list.");
            return;
        }
        System.out.println("Movies (Reverse):");
        while (current != null) {
            System.out.println("Title: " + current.title + ", Director: " + current.director +
                    ", Year: " + current.year + ", Rating: " + current.rating);
            current = current.prev;
        }
    }
}

public class MovieManagementSystem {
    public static void main(String[] args) {
        MovieDoublyLinkedList movieList = new MovieDoublyLinkedList();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Movie Management System ---");
            System.out.println("1. Add Movie at Beginning");
            System.out.println("2. Add Movie at End");
            System.out.println("3. Add Movie at Specific Position");
            System.out.println("4. Remove Movie by Title");
            System.out.println("5. Search Movie by Director");
            System.out.println("6. Search Movie by Rating");
            System.out.println("7. Update Movie Rating");
            System.out.println("8. Display All Movies (Forward)");
            System.out.println("9. Display All Movies (Reverse)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // clear buffer

            switch (choice) {
                case 1:
                case 2:
                case 3: {
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Director: ");
                    String director = sc.nextLine();
                    System.out.print("Enter Year of Release: ");
                    int year = sc.nextInt();
                    System.out.print("Enter Rating: ");
                    double rating = sc.nextDouble();
                    Movie movie = new Movie(title, director, year, rating);
                    if (choice == 1)
                        movieList.addAtBeginning(movie);
                    else if (choice == 2)
                        movieList.addAtEnd(movie);
                    else {
                        System.out.print("Enter position: ");
                        int pos = sc.nextInt();
                        movieList.addAtPosition(movie, pos);
                    }
                    break;
                }
                case 4: {
                    System.out.print("Enter Title to Remove: ");
                    String title = sc.nextLine();
                    movieList.removeByTitle(title);
                    break;
                }
                case 5: {
                    System.out.print("Enter Director Name: ");
                    String director = sc.nextLine();
                    movieList.searchByDirectorOrRating(director, null);
                    break;
                }
                case 6: {
                    System.out.print("Enter Rating: ");
                    double rating = sc.nextDouble();
                    movieList.searchByDirectorOrRating(null, rating);
                    break;
                }
                case 7: {
                    System.out.print("Enter Movie Title to Update Rating: ");
                    String title = sc.nextLine();
                    System.out.print("Enter New Rating: ");
                    double newRating = sc.nextDouble();
                    if (movieList.updateRating(title, newRating))
                        System.out.println("Rating updated successfully.");
                    else
                        System.out.println("Movie not found.");
                    break;
                }
                case 8:
                    movieList.displayForward();
                    break;
                case 9:
                    movieList.displayReverse();
                    break;
                case 0:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        sc.close();
    }
}

