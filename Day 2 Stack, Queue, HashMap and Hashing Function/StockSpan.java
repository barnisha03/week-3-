import java.util.Stack;

public class StockSpan {
    public static int[] calculateSpan(int[] prices) {
        int n = prices.length;
        int[] span = new int[n];
        Stack<Integer> stack = new Stack<>(); // store indices
        
        for (int i = 0; i < n; i++) {
            // Pop elements from stack while current price is higher than price at top of stack
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
                stack.pop();
            }
            
            // If stack empty, span = i + 1 else difference between current and top of stack
            span[i] = stack.isEmpty() ? (i + 1) : (i - stack.peek());
            
            stack.push(i);
        }
        
        return span;
    }
    
    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        int[] spans = calculateSpan(prices);
        System.out.print("Stock spans: ");
        for (int s : spans) {
            System.out.print(s + " ");
        }
    }
}









