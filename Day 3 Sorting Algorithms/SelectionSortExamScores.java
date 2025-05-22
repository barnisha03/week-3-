public class SelectionSortExamScores {

    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            // Find the minimum element in unsorted part
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap min element with first unsorted element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void printArray(int[] arr) {
        for (int score : arr) {
            System.out.print(score + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] examScores = {88, 74, 92, 65, 89, 78};

        System.out.println("Before sorting:");
        printArray(examScores);

        selectionSort(examScores);

        System.out.println("After sorting:");
        printArray(examScores);
    }
}





