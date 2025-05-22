class Process {
    int processId, burstTime, priority, remainingTime;
    Process next;

    public Process(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
    }
}

class RoundRobinScheduler {
    Process head = null, tail = null;

    void addProcess(int processId, int burstTime, int priority) {
        Process newProcess = new Process(processId, burstTime, priority);
        if (head == null) {
            head = tail = newProcess;
            tail.next = head;
        } else {
            tail.next = newProcess;
            tail = newProcess;
            tail.next = head;
        }
    }

    void removeProcess(int processId) {
        if (head == null) return;

        Process current = head, prev = null;
        do {
            if (current.processId == processId) {
                if (current == head && current == tail) {
                    head = tail = null;
                } else if (current == head) {
                    head = head.next;
                    tail.next = head;
                } else {
                    prev.next = current.next;
                    if (current == tail) {
                        tail = prev;
                    }
                }
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }

    void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in queue.");
            return;
        }
        Process temp = head;
        System.out.println("Process Queue:");
        do {
            System.out.println("Process ID: " + temp.processId + ", Remaining Time: " + temp.remainingTime);
            temp = temp.next;
        } while (temp != head);
    }

    void simulateRoundRobin(int timeQuantum) {
        if (head == null) return;

        int totalTime = 0, completed = 0;
        int waitingTime = 0, turnAroundTime = 0;
        int processCount = getCount();

        Process current = head;

        while (completed < processCount) {
            if (current.remainingTime > 0) {
                if (current.remainingTime > timeQuantum) {
                    totalTime += timeQuantum;
                    current.remainingTime -= timeQuantum;
                } else {
                    totalTime += current.remainingTime;
                    waitingTime += (totalTime - current.burstTime);
                    turnAroundTime += totalTime;
                    current.remainingTime = 0;
                    completed++;
                    removeProcess(current.processId);
                }
                displayProcesses();
            }
            current = current.next;
        }

        System.out.println("\nAverage Waiting Time: " + (float) waitingTime / processCount);
        System.out.println("Average Turnaround Time: " + (float) turnAroundTime / processCount);
    }

    int getCount() {
        if (head == null) return 0;
        int count = 0;
        Process temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }
}

public class RoundRobinMain {
    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler();
        scheduler.addProcess(1, 5, 2);
        scheduler.addProcess(2, 3, 1);
        scheduler.addProcess(3, 8, 3);
        scheduler.addProcess(4, 6, 2);

        scheduler.simulateRoundRobin(3);
    }
}





