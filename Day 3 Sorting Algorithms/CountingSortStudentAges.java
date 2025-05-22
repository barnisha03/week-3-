public class CountingSortStudentAges {

    public static void countingSort(int[] ages) {
        int minAge = 10;
        int maxAge = 18;
        int range = maxAge - minAge + 1;

        int[] count = new int[range];
        int[] output = new int[ages.length];

        // Count frequency of each age
        for (int age : ages) {
            count[age - minAge]++;
        }

        // Compute cumulative count
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        // Build output array from end to maintain stability
        for (int i = ages.length - 1; i >= 0; i--) {
            int age = ages[i];
            output[count[age - minAge] - 1] = age;
            count[age - minAge]--;
        }

        // Copy sorted output back to original array
        System.arraycopy(output, 0, ages, 0, ages.length);
    }

    public static void printArray(int[] arr) {
        for (int age : arr) {
            System.out.print(age + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] studentAges = {12, 15, 10, 18, 14, 13, 10, 17, 15};

        System.out.println("Before sorting:");
        printArray(studentAges);

        countingSort(studentAges);

        System.out.println("After sorting:");
        printArray(studentAges);
    }
}








