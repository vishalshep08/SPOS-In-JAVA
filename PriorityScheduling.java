import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Process {
    int pid, arrival, burst, priority, completion, turnaround, waiting;

    public Process(int pid, int arrival, int burst, int priority) {
        this.pid = pid;
        this.arrival = arrival;
        this.burst = burst;
        this.priority = priority;
        this.completion = 0;
        this.turnaround = 0;
        this.waiting = 0;
    }
}

public class PriorityScheduling {
    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        processes.add(new Process(1, 0, 8, 3));
        processes.add(new Process(2, 1, 4, 1));
        processes.add(new Process(3, 2, 9, 4));
        processes.add(new Process(4, 3, 5, 2));

        // Sort by arrival time, then by priority
        Collections.sort(processes, Comparator.comparingInt((Process p) -> p.arrival)
                .thenComparingInt(p -> p.priority));

        int currentTime = 0;

        for (Process p : processes) {
            currentTime = Math.max(currentTime, p.arrival) + p.burst;
            p.completion = currentTime;
            p.turnaround = p.completion - p.arrival;
            p.waiting = p.turnaround - p.burst;
        }

        System.out.println("Priority Scheduling (Non-Preemptive)");
        System.out.printf("%-5s %-8s %-7s %-9s %-11s %-11s %-7s\n",
                "PID", "Arrival", "Burst", "Priority", "Completion", "Turnaround", "Waiting");

        double totalTurnaround = 0, totalWaiting = 0;

        for (Process p : processes) {
            System.out.printf("%-5d %-8d %-7d %-9d %-11d %-11d %-7d\n",
                    p.pid, p.arrival, p.burst, p.priority, p.completion, p.turnaround, p.waiting);
            totalTurnaround += p.turnaround;
            totalWaiting += p.waiting;
        }

        System.out.printf("\nAverage Turnaround Time: %.2f\n", totalTurnaround / processes.size());
        System.out.printf("Average Waiting Time: %.2f\n", totalWaiting / processes.size());
    }
}
