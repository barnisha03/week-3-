import java.util.Scanner;

class Task {
    int taskId;
    String taskName;
    int priority;
    String dueDate;
    Task next;

    public Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class CircularTaskList {
    private Task head = null;
    private Task tail = null;
    private Task currentTask = null;

    // Add task at beginning
    public void addAtBeginning(Task newTask) {
        if (head == null) {
            head = tail = currentTask = newTask;
            newTask.next = newTask;
        } else {
            newTask.next = head;
            tail.next = newTask;
            head = newTask;
        }
    }

    // Add task at end
    public void addAtEnd(Task newTask) {
        if (head == null) {
            addAtBeginning(newTask);
        } else {
            newTask.next = head;
            tail.next = newTask;
            tail = newTask;
        }
    }

    // Add at specific position (1-based)
    public void addAtPosition(Task newTask, int position) {
        if (position <= 1 || head == null) {
            addAtBeginning(newTask);
            return;
        }

        Task current = head;
        int count = 1;
        while (count < position - 1 && current.next != head) {
            current = current.next;
            count++;
        }

        newTask.next = current.next;
        current.next = newTask;

        if (current == tail) {
            tail = newTask;
        }
    }

    // Remove task by ID
    public void removeByTaskId(int id) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Task current = head;
        Task previous = tail;
        boolean found = false;

        do {
            if (current.taskId == id) {
                found = true;
                if (current == head && current == tail) { // only one node
                    head = tail = currentTask = null;
                } else {
                    previous.next = current.next;
                    if (current == head) head = current.next;
                    if (current == tail) tail = previous;
                    if (current == currentTask) currentTask = current.next;
                }
                System.out.println("Task with ID " + id + " removed.");
                break;
            }
            previous = current;
            current = current.next;
        } while (current != head);

        if (!found) {
            System.out.println("Task with ID " + id + " not found.");
        }
    }

    // View current task and move to next
    public void viewAndMoveToNext() {
        if (currentTask == null) {
            System.out.println("No tasks to view.");
            return;
        }

        System.out.println("Current Task:");
        System.out.println("ID: " + currentTask.taskId + ", Name: " + currentTask.taskName +
                ", Priority: " + currentTask.priority + ", Due: " + currentTask.dueDate);

        currentTask = currentTask.next;
    }

    // Display all tasks from head
    public void displayAllTasks() {
        if (head == null) {
            System.out.println("No tasks in the list.");
            return;
        }

        System.out.println("All Tasks:");
        Task current = head;
        do {
            System.out.println("ID: " + current.taskId + ", Name: " + current.taskName +
                    ", Priority: " + current.priority + ", Due: " + current.dueDate);
            current = current.next;
        } while (current != head);
    }

    // Search by priority
    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Task current = head;
        boolean found = false;

        do {
            if (current.priority == priority) {
                System.out.println("ID: " + current.taskId + ", Name: " + current.taskName +
                        ", Priority: " + current.priority + ", Due: " + current.dueDate);
                found = true;
            }
            current = current.next;
        } while (current != head);

        if (!found) {
            System.out.println("No tasks with priority " + priority + " found.");
        }
    }
}

public class TaskScheduler {
    public static void main(String[] args) {
        CircularTaskList taskList = new CircularTaskList();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Task Scheduler Menu ---");
            System.out.println("1. Add Task at Beginning");
            System.out.println("2. Add Task at End");
            System.out.println("3. Add Task at Specific Position");
            System.out.println("4. Remove Task by ID");
            System.out.println("5. View Current Task and Move to Next");
            System.out.println("6. Display All Tasks");
            System.out.println("7. Search Task by Priority");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                case 2:
                case 3: {
                    System.out.print("Enter Task ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Task Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Priority (1-5): ");
                    int priority = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Due Date (DD-MM-YYYY): ");
                    String due = sc.nextLine();

                    Task newTask = new Task(id, name, priority, due);
                    if (choice == 1)
                        taskList.addAtBeginning(newTask);
                    else if (choice == 2)
                        taskList.addAtEnd(newTask);
                    else {
                        System.out.print("Enter Position: ");
                        int pos = sc.nextInt();
                        taskList.addAtPosition(newTask, pos);
                    }
                    break;
                }

                case 4:
                    System.out.print("Enter Task ID to remove: ");
                    int removeId = sc.nextInt();
                    taskList.removeByTaskId(removeId);
                    break;

                case 5:
                    taskList.viewAndMoveToNext();
                    break;

                case 6:
                    taskList.displayAllTasks();
                    break;

                case 7:
                    System.out.print("Enter Priority to search (1-5): ");
                    int p = sc.nextInt();
                    taskList.searchByPriority(p);
                    break;

                case 0:
                    System.out.println("Exiting Task Scheduler...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        sc.close();
    }
}


