import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        private final long timeSleep;
        private final String threadName;
        public Task(String threadName, long timeSleep) {
            this.timeSleep = timeSleep;
            this.threadName = threadName;
        }

        @Override
        public void run() {
            try(PrintWriter writer = new PrintWriter(new FileWriter("D:\\POE\\Lab5\\file.txt",true))){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                while(!Thread.currentThread().isInterrupted() && counter <=240){
                    writer.printf("%s: %s - %d%n",threadName,dateFormat.format(new Date()),counter++);
                    Thread.sleep(timeSleep);
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
