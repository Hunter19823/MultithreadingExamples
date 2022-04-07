package task;

import static java.lang.Thread.sleep;

public class ThreadRace implements Runnable {
    private final Thread[] threads;
    public ThreadRace(Thread... threads) {
        this.threads = threads;
    }

    @Override
    public void run() {
        System.out.println("3 2 1 Race!");
        for (Thread thread : threads) {
            thread.start();
        }
        boolean threadsAlive = true;
        while(threadsAlive) {
            threadsAlive = false;
            for (Thread thread : threads) {
                if (thread.isAlive()) {
                    threadsAlive = true;
                    System.out.println("Thread " + thread.getName() + " is still alive!");
                }
            }
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("All threads have finished!");
    }
}
