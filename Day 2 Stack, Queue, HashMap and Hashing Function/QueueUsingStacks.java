import java.util.Stack;

public class QueueUsingStacks {
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    // Enqueue operation: push into stack1
    public void enqueue(int x) {
        stack1.push(x);
    }

    // Dequeue operation: pop from stack2 if not empty; else transfer all from stack1 to stack2 then pop
    public int dequeue() {
        if (stack2.isEmpty()) {
            if (stack1.isEmpty()) {
                throw new RuntimeException("Queue is empty");
            }
            // Transfer all elements from stack1 to stack2
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    // Peek the front element without removing
    public int peek() {
        if (stack2.isEmpty()) {
            if (stack1.isEmpty()) {
                throw new RuntimeException("Queue is empty");
            }
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public static void main(String[] args) {
        QueueUsingStacks queue = new QueueUsingStacks();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println(queue.dequeue()); // Output: 10
        queue.enqueue(40);
        System.out.println(queue.dequeue()); // Output: 20
        System.out.println(queue.peek());    // Output: 30
        System.out.println(queue.isEmpty()); // Output: false
    }
}







