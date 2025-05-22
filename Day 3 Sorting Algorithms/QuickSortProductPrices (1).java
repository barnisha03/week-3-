public class QuickSortProductPrices {

    public static void quickSort(double[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);

            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(double[] arr, int low, int high) {
        double pivot = arr[high]; // Using last element as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                double temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i+1] and arr[high] (pivot)
        double temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void printArray(double[] arr) {
        for (double price : arr) {
            System.out.print(price + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        double[] productPrices = {29.99, 10.99, 15.49, 8.99, 22.50, 18.75};

        System.out.println("Before sorting:");
        printArray(productPrices);

        quickSort(productPrices, 0, productPrices.length - 1);

        System.out.println("After sorting:");
        printArray(productPrices);
    }
}






