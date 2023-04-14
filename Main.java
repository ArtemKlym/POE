import java.io.*;
import java.time.LocalDateTime;

public class Main {
    private static volatile int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread task1 = new Thread(new Task("Thread1",250));
        Thread task2 = new Thread(new Task("Thread2",500));
        Thread task3 = new Thread(new Task("Thread3",1000));

        task1.start();
        task2.start();
        task3.start();

        while (counter <= 240) {
            Thread.sleep(1000);
        }

        task1.interrupt();
        task2.interrupt();
        task3.interrupt();
    }

    private static class Task implements Runnable {
        private final String threadName;
        private final int delay;

        public Task(String threadName, int delay) {
            this.threadName = threadName;
            this.delay = delay;
        }

        @Override
        public void run() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\POE\\Lab5\\file.txt", true))) {
                while (!Thread.interrupted() && counter <= 240) {
                    LocalDateTime now = LocalDateTime.now();
                    String line = String.format("%s - %s - %d\n", threadName, now.toString(), counter);
                    writer.write(line);
                    writer.flush();
                    counter++;
                    Thread.sleep(delay);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
