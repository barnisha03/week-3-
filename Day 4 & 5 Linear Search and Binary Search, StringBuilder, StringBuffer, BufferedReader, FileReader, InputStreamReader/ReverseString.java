public class ReverseString {
    public static String reverseString(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        sb.reverse();  // Reverse the string using StringBuilder method
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "hello";
        String reversed = reverseString(input);
        System.out.println("Original String: " + input);
        System.out.println("Reversed String: " + reversed);
    }
}









