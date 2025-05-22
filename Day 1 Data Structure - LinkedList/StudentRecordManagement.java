import java.util.Scanner;

class Student {
    int rollNumber;
    String name;
    int age;
    String grade;
    Student next;

    public Student(int rollNumber, String name, int age, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentLinkedList {
    private Student head;

    // Add at beginning
    public void addAtBeginning(Student newStudent) {
        newStudent.next = head;
        head = newStudent;
    }

    // Add at end
    public void addAtEnd(Student newStudent) {
        if (head == null) {
            head = newStudent;
            return;
        }
        Student current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newStudent;
    }

    // Add at specific position (1-based index)
    public void addAtPosition(Student newStudent, int position) {
        if (position <= 1) {
            addAtBeginning(newStudent);
            return;
        }
        Student current = head;
        int count = 1;
        while (current != null && count < position - 1) {
            current = current.next;
            count++;
        }
        if (current == null) {
            addAtEnd(newStudent);
        } else {
            newStudent.next = current.next;
            current.next = newStudent;
        }
    }

    // Delete by roll number
    public void deleteByRollNumber(int rollNumber) {
        if (head == null) return;
        if (head.rollNumber == rollNumber) {
            head = head.next;
            return;
        }
        Student current = head;
        while (current.next != null && current.next.rollNumber != rollNumber) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    // Search by roll number
    public Student searchByRollNumber(int rollNumber) {
        Student current = head;
        while (current != null) {
            if (current.rollNumber == rollNumber) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Update grade by roll number
    public boolean updateGradeByRollNumber(int rollNumber, String newGrade) {
        Student student = searchByRollNumber(rollNumber);
        if (student != null) {
            student.grade = newGrade;
            return true;
        }
        return false;
    }

    // Display all student records
    public void displayAll() {
        if (head == null) {
            System.out.println("No student records found.");
            return;
        }
        Student current = head;
        System.out.println("Student Records:");
        while (current != null) {
            System.out.println("Roll Number: " + current.rollNumber +
                               ", Name: " + current.name +
                               ", Age: " + current.age +
                               ", Grade: " + current.grade);
            current = current.next;
        }
    }
}

public class StudentRecordManagement {
    public static void main(String[] args) {
        StudentLinkedList studentList = new StudentLinkedList();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Student Record Management ---");
            System.out.println("1. Add Student at Beginning");
            System.out.println("2. Add Student at End");
            System.out.println("3. Add Student at Specific Position");
            System.out.println("4. Delete Student by Roll Number");
            System.out.println("5. Search Student by Roll Number");
            System.out.println("6. Update Student Grade");
            System.out.println("7. Display All Students");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                case 2:
                case 3: {
                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();
                    sc.nextLine(); // clear newline
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();
                    Student s = new Student(roll, name, age, grade);
                    if (choice == 1)
                        studentList.addAtBeginning(s);
                    else if (choice == 2)
                        studentList.addAtEnd(s);
                    else {
                        System.out.print("Enter position: ");
                        int pos = sc.nextInt();
                        studentList.addAtPosition(s, pos);
                    }
                    break;
                }
                case 4: {
                    System.out.print("Enter Roll Number to delete: ");
                    int roll = sc.nextInt();
                    studentList.deleteByRollNumber(roll);
                    break;
                }
                case 5: {
                    System.out.print("Enter Roll Number to search: ");
                    int roll = sc.nextInt();
                    Student found = studentList.searchByRollNumber(roll);
                    if (found != null) {
                        System.out.println("Student Found: " + found.name + ", Age: " + found.age + ", Grade: " + found.grade);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                }
                case 6: {
                    System.out.print("Enter Roll Number to update: ");
                    int roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new Grade: ");
                    String grade = sc.nextLine();
                    boolean updated = studentList.updateGradeByRollNumber(roll, grade);
                    if (updated)
                        System.out.println("Grade updated successfully.");
                    else
                        System.out.println("Student not found.");
                    break;
                }
                case 7:
                    studentList.displayAll();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        sc.close();
    }
}
