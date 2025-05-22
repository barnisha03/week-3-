import java.util.*;

public class ZeroSumSubarrays {
    public static void findZeroSumSubarrays(int[] arr) {
        // Map to store cumulative sum and list of indices where this sum occurred
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        int sum = 0;
        // Initialize with sum 0 at index -1 to handle subarrays starting at index 0
        map.put(0, new ArrayList<>(Arrays.asList(-1)));
        
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            
            // If sum is seen before, all subarrays between previous indices+1 and current index have zero sum
            if (map.containsKey(sum)) {
                List<Integer> list = map.get(sum);
                for (int startIndex : list) {
                    System.out.println("Zero-sum subarray found from index " + (startIndex + 1) + " to " + i);
                }
                list.add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(sum, list);
            }
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {3, 4, -7, 1, 3, -4, -2, -2};
        findZeroSumSubarrays(arr);
    }
}












