import java.util.Stack;

public class SortStackRecursion {

    // Function to sort the stack recursively
    public static void sortStack(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            // Pop the top element
            int top = stack.pop();
            
            // Sort the remaining stack
            sortStack(stack);
            
            // Insert the popped element in the sorted stack
            insertSorted(stack, top);
        }
    }

    // Helper function to insert an element into the sorted stack at correct position
    private static void insertSorted(Stack<Integer> stack, int element) {
        // Base case: if stack is empty or top element is less than or equal to element
        if (stack.isEmpty() || stack.peek() <= element) {
            stack.push(element);
            return;
        }
        
        // If top element is greater, pop it and recurse
        int top = stack.pop();
        insertSorted(stack, element);
        
        // Push the popped element back
        stack.push(top);
    }

    // Main function to test sorting
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(30);
        stack.push(10);
        stack.push(20);
        stack.push(5);
        stack.push(40);

        System.out.println("Original Stack: " + stack);
        sortStack(stack);
        System.out.println("Sorted Stack: " + stack);
    }
}








