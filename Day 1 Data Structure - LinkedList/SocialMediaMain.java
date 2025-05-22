import java.util.*;

class User {
    int userId;
    String name;
    int age;
    List<Integer> friendIds;
    User next;

    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
    }
}

class SocialMedia {
    User head = null;

    void addUser(User user) {
        if (head == null) {
            head = user;
        } else {
            User temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = user;
        }
    }

    User getUserById(int id) {
        User temp = head;
        while (temp != null) {
            if (temp.userId == id) return temp;
            temp = temp.next;
        }
        return null;
    }

    void addFriend(int id1, int id2) {
        User u1 = getUserById(id1);
        User u2 = getUserById(id2);
        if (u1 != null && u2 != null) {
            if (!u1.friendIds.contains(id2)) u1.friendIds.add(id2);
            if (!u2.friendIds.contains(id1)) u2.friendIds.add(id1);
        }
    }

    void removeFriend(int id1, int id2) {
        User u1 = getUserById(id1);
        User u2 = getUserById(id2);
        if (u1 != null && u2 != null) {
            u1.friendIds.remove(Integer.valueOf(id2));
            u2.friendIds.remove(Integer.valueOf(id1));
        }
    }

    void mutualFriends(int id1, int id2) {
        User u1 = getUserById(id1);
        User u2 = getUserById(id2);
        if (u1 != null && u2 != null) {
            Set<Integer> set = new HashSet<>(u1.friendIds);
            set.retainAll(u2.friendIds);
            System.out.println("Mutual Friends of " + id1 + " and " + id2 + ": " + set);
        }
    }

    void displayFriends(int id) {
        User user = getUserById(id);
        if (user != null) {
            System.out.println("Friends of " + user.name + ": " + user.friendIds);
        }
    }

    void searchUser(String keyword) {
        User temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(keyword) || String.valueOf(temp.userId).equals(keyword)) {
                System.out.println("Found: " + temp.name + ", ID: " + temp.userId);
                return;
            }
            temp = temp.next;
        }
        System.out.println("User not found.");
    }

    void countFriends() {
        User temp = head;
        while (temp != null) {
            System.out.println(temp.name + " has " + temp.friendIds.size() + " friends.");
            temp = temp.next;
        }
    }
}

public class SocialMediaMain {
    public static void main(String[] args) {
        SocialMedia sm = new SocialMedia();

        sm.addUser(new User(1, "Alice", 22));
        sm.addUser(new User(2, "Bob", 24));
        sm.addUser(new User(3, "Charlie", 23));
        sm.addUser(new User(4, "Daisy", 21));

        sm.addFriend(1, 2);
        sm.addFriend(1, 3);
        sm.addFriend(2, 3);
        sm.addFriend(3, 4);

        sm.displayFriends(1);
        sm.displayFriends(2);
        sm.mutualFriends(1, 2);
        sm.removeFriend(1, 2);
        sm.displayFriends(1);

        sm.searchUser("Charlie");
        sm.countFriends();
    }
}





