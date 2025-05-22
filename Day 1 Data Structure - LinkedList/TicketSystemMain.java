class Ticket {
    int ticketId;
    String customerName, movieName, seatNumber, bookingTime;
    Ticket next;

    public Ticket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
    }
}

class TicketSystem {
    Ticket head = null;

    void addTicket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = newTicket;
            head.next = head;
        } else {
            Ticket temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTicket;
            newTicket.next = head;
        }
    }

    void removeTicket(int ticketId) {
        if (head == null) return;
        Ticket current = head, prev = null;

        do {
            if (current.ticketId == ticketId) {
                if (current == head) {
                    if (head.next == head) {
                        head = null;
                        return;
                    }
                    Ticket last = head;
                    while (last.next != head) last = last.next;
                    head = head.next;
                    last.next = head;
                } else {
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }

    void displayTickets() {
        if (head == null) {
            System.out.println("No tickets available.");
            return;
        }
        Ticket temp = head;
        do {
            System.out.println("Ticket ID: " + temp.ticketId + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName +
                    ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }

    void searchTicket(String keyword) {
        if (head == null) return;
        Ticket temp = head;
        boolean found = false;
        do {
            if (temp.customerName.equalsIgnoreCase(keyword) || temp.movieName.equalsIgnoreCase(keyword)) {
                System.out.println("Found - Ticket ID: " + temp.ticketId + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) System.out.println("No matching ticket found.");
    }

    void countTickets() {
        if (head == null) {
            System.out.println("Total tickets: 0");
            return;
        }
        Ticket temp = head;
        int count = 0;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        System.out.println("Total tickets: " + count);
    }
}

public class TicketSystemMain {
    public static void main(String[] args) {
        TicketSystem system = new TicketSystem();
        system.addTicket(1, "Alice", "Inception", "A1", "10:00 AM");
        system.addTicket(2, "Bob", "Avatar", "B2", "12:00 PM");
        system.addTicket(3, "Charlie", "Inception", "C3", "3:00 PM");

        system.displayTickets();

        system.removeTicket(2);
        System.out.println("\nAfter removing Ticket ID 2:");
        system.displayTickets();

        system.searchTicket("Inception");
        system.countTickets();
    }
}







