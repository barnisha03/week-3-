public class MergeSortBookPrices {

    public static void mergeSort(double[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Sort first half
            mergeSort(arr, left, mid);
            // Sort second half
            mergeSort(arr, mid + 1, right);
            // Merge sorted halves
            merge(arr, left, mid, right);
        }
    }

    public static void merge(double[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        double[] leftArr = new double[n1];
        double[] rightArr = new double[n2];

        for (int i = 0; i < n1; i++)
            leftArr[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            rightArr[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        // Merge the two arrays back into arr
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        // Copy remaining elements of leftArr, if any
        while (i < n1) {
            arr[k++] = leftArr[i++];
        }

        // Copy remaining elements of rightArr, if any
        while (j < n2) {
            arr[k++] = rightArr[j++];
        }
    }

    public static void printArray(double[] arr) {
        for (double price : arr) {
            System.out.print(price + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        double[] bookPrices = {15.99, 9.50, 23.75, 12.40, 7.80, 19.99};

        System.out.println("Before sorting:");
        printArray(bookPrices);

        mergeSort(bookPrices, 0, bookPrices.length - 1);

        System.out.println("After sorting:");
        printArray(bookPrices);
    }
}



