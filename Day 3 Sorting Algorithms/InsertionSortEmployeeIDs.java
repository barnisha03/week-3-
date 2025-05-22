public class InsertionSortEmployeeIDs {

    public static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // Move elements of arr[0..i-1], that are greater than key, to one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Insert key at the correct position
            arr[j + 1] = key;
        }
    }

    public static void printArray(int[] arr) {
        for (int id : arr) {
            System.out.print(id + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] employeeIDs = {102, 45, 78, 10, 56, 89};

        System.out.println("Before sorting:");
        printArray(employeeIDs);

        insertionSort(employeeIDs);

        System.out.println("After sorting:");
        printArray(employeeIDs);
    }
}


