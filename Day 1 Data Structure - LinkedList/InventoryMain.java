import java.util.*;

class InventoryItem {
    String itemName;
    int itemId;
    int quantity;
    double price;
    InventoryItem next;

    public InventoryItem(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class InventoryManagement {
    InventoryItem head = null;

    void addItemAtBeginning(String itemName, int itemId, int quantity, double price) {
        InventoryItem newItem = new InventoryItem(itemName, itemId, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    void addItemAtEnd(String itemName, int itemId, int quantity, double price) {
        InventoryItem newItem = new InventoryItem(itemName, itemId, quantity, price);
        if (head == null) {
            head = newItem;
            return;
        }
        InventoryItem temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = newItem;
    }

    void addItemAtPosition(String itemName, int itemId, int quantity, double price, int position) {
        if (position == 0) {
            addItemAtBeginning(itemName, itemId, quantity, price);
            return;
        }
        InventoryItem newItem = new InventoryItem(itemName, itemId, quantity, price);
        InventoryItem temp = head;
        for (int i = 0; temp != null && i < position - 1; i++) temp = temp.next;
        if (temp == null) return;
        newItem.next = temp.next;
        temp.next = newItem;
    }

    void removeItemById(int itemId) {
        if (head == null) return;
        if (head.itemId == itemId) {
            head = head.next;
            return;
        }
        InventoryItem temp = head;
        while (temp.next != null && temp.next.itemId != itemId) temp = temp.next;
        if (temp.next != null) temp.next = temp.next.next;
    }

    void updateQuantityById(int itemId, int newQuantity) {
        InventoryItem temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) {
                temp.quantity = newQuantity;
                return;
            }
            temp = temp.next;
        }
    }

    void searchItem(String key) {
        InventoryItem temp = head;
        while (temp != null) {
            if (String.valueOf(temp.itemId).equals(key) || temp.itemName.equalsIgnoreCase(key)) {
                System.out.println(temp.itemName + " | ID: " + temp.itemId + " | Qty: " + temp.quantity + " | Price: " + temp.price);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    void displayItems() {
        InventoryItem temp = head;
        while (temp != null) {
            System.out.println(temp.itemName + " | ID: " + temp.itemId + " | Qty: " + temp.quantity + " | Price: " + temp.price);
            temp = temp.next;
        }
    }

    void calculateTotalValue() {
        double total = 0;
        InventoryItem temp = head;
        while (temp != null) {
            total += temp.price * temp.quantity;
            temp = temp.next;
        }
        System.out.println("Total Inventory Value: " + total);
    }

    void sortInventory(String key, boolean ascending) {
        head = mergeSort(head, key, ascending);
    }

    private InventoryItem mergeSort(InventoryItem node, String key, boolean ascending) {
        if (node == null || node.next == null) return node;
        InventoryItem middle = getMiddle(node);
        InventoryItem nextOfMiddle = middle.next;
        middle.next = null;
        InventoryItem left = mergeSort(node, key, ascending);
        InventoryItem right = mergeSort(nextOfMiddle, key, ascending);
        return sortedMerge(left, right, key, ascending);
    }

    private InventoryItem sortedMerge(InventoryItem a, InventoryItem b, String key, boolean ascending) {
        if (a == null) return b;
        if (b == null) return a;
        InventoryItem result;
        boolean condition;
        if (key.equalsIgnoreCase("name"))
            condition = ascending ? a.itemName.compareTo(b.itemName) <= 0 : a.itemName.compareTo(b.itemName) > 0;
        else
            condition = ascending ? a.price <= b.price : a.price > b.price;

        if (condition) {
            result = a;
            result.next = sortedMerge(a.next, b, key, ascending);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next, key, ascending);
        }
        return result;
    }

    private InventoryItem getMiddle(InventoryItem head) {
        if (head == null) return head;
        InventoryItem slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

public class InventoryMain {
    public static void main(String[] args) {
        InventoryManagement inv = new InventoryManagement();
        inv.addItemAtEnd("Monitor", 101, 10, 199.99);
        inv.addItemAtBeginning("Mouse", 102, 25, 19.99);
        inv.addItemAtEnd("Keyboard", 103, 15, 49.99);
        inv.addItemAtPosition("Laptop", 104, 5, 999.99, 2);

        System.out.println("Inventory:");
        inv.displayItems();

        inv.updateQuantityById(103, 20);
        System.out.println("\nUpdated Inventory:");
        inv.displayItems();

        inv.searchItem("104");
        inv.searchItem("Mouse");

        System.out.println("\nTotal Value:");
        inv.calculateTotalValue();

        inv.sortInventory("price", true);
        System.out.println("\nSorted Inventory by Price Ascending:");
        inv.displayItems();
    }
}



