import java.util.HashMap;

public class TwoSum {

    // Returns indices of two numbers that add up to target
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(); // value -> index

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // Check if complement already exists in the map
            if (map.containsKey(complement)) {
                // Return indices of the two elements
                return new int[]{map.get(complement), i};
            }

            // Store current number and its index in the map
            map.put(nums[i], i);
        }

        // Return empty array if no pair found
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] result = twoSum(nums, target);
        if (result.length == 2) {
            System.out.println("Indices found: " + result[0] + ", " + result[1]);
        } else {
            System.out.println("No pair found");
        }
    }
}
