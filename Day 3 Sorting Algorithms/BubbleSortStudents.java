public class BubbleSortStudents {

    // Bubble sort method to sort marks array in ascending order
    public static void bubbleSort(int[] marks) {
        int n = marks.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            // Compare adjacent elements and swap if needed
            for (int j = 0; j < n - i - 1; j++) {
                if (marks[j] > marks[j + 1]) {
                    // Swap marks[j] and marks[j+1]
                    int temp = marks[j];
                    marks[j] = marks[j + 1];
                    marks[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no elements were swapped in the inner loop, array is sorted
            if (!swapped) break;
        }
    }

    // Utility method to print the array
    public static void printArray(int[] arr) {
        for (int mark : arr) {
            System.out.print(mark + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] studentMarks = {85, 70, 95, 60, 75, 90};

        System.out.println("Before sorting:");
        printArray(studentMarks);

        bubbleSort(studentMarks);

        System.out.println("After sorting:");
        printArray(studentMarks);
    }
}

