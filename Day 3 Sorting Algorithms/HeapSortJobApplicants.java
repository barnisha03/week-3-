public class HeapSortJobApplicants {

    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i >= 0; i--) {
            // Move current root (largest) to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Call max heapify on reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i, size n
    private static void heapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Check if left child is larger
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // Check if right child is larger
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // Swap and continue heapifying if root is not largest
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    public static void printArray(int[] arr) {
        for (int salary : arr) {
            System.out.print(salary + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] salaries = {50000, 75000, 62000, 48000, 80000, 70000};

        System.out.println("Before sorting:");
        printArray(salaries);

        heapSort(salaries);

        System.out.println("After sorting:");
        printArray(salaries);
    }
}







