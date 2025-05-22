import java.util.HashSet;

public class PairWithSum {
    public static boolean hasPairWithSum(int[] arr, int target) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) {
            if (set.contains(target - num)) {
                return true; // pair found
            }
            set.add(num);
        }
        return false; // no pair found
    }

    public static void main(String[] args) {
        int[] arr = {10, 15, 3, 7};
        int target = 17;
        System.out.println("Pair exists: " + hasPairWithSum(arr, target)); // Output: true
    }
}













