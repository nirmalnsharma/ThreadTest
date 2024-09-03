package main.java.print_odd_even_numbers_alternatively;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AlternatingThreadsWithExecutor {
    private static final Object lock = new Object();
    private static boolean isOddTurn = true;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Runnable oddTask = new NumberPrinter(true); // Odd numbers
        Runnable evenTask = new NumberPrinter(false); // Even numbers
        executor.submit(oddTask);
        executor.submit(evenTask);
        executor.shutdown(); // Initiate an orderly shutdown of the executor
    }

    static class NumberPrinter implements Runnable {
        private final boolean isOdd;

        public NumberPrinter(boolean isOdd) {
            this.isOdd = isOdd;
        }

        @Override
        public void run() {
            for (int i = isOdd ? 1 : 2; i <= 100; i += 2) {
                synchronized (lock) {
                    while (isOdd != isOddTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println(i);
                    isOddTurn = !isOddTurn; // Toggle the turn
                    lock.notifyAll(); // Notify the other thread
                }
            }
        }
    }
}
