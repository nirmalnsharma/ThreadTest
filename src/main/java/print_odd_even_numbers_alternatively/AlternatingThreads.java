package main.java.print_odd_even_numbers_alternatively;

import java.util.stream.IntStream;

public class AlternatingThreads {

    private static final Object lock = new Object();
    private static boolean isOddTurn = true; // Start with odd numbers

    public static void main(String[] args) {
        Thread oddThread = new Thread(new NumberPrinter(true)); // Odd numbers
        Thread evenThread = new Thread(new NumberPrinter(false)); // Even numbers

        oddThread.start();
        evenThread.start();
    }

    static class NumberPrinter implements Runnable {
        private final boolean isOdd;

        public NumberPrinter(boolean isOdd) {
            this.isOdd = isOdd;
        }

        @Override
        public void run() {
            IntStream.iterate(isOdd ? 1 : 2, i -> i <= 100, i -> i + 2).forEach(i -> {
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
            });
        }
    }
}
