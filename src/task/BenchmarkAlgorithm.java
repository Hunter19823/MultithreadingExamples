package task;

import java.util.function.Consumer;

public class BenchmarkAlgorithm<DATA extends Comparable<DATA>> extends SortOneDimensionalTask<DATA> {
    public String name;
    public BenchmarkAlgorithm(String name, Consumer<DATA[]> algorithm, DATA[] data) {
        super(algorithm, data);
        this.name = name;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        long endTime;
        long duration;
        try {
            algorithm.accept(data);
            endTime = System.currentTimeMillis();
            duration = endTime - startTime;
            System.out.println("Thread " + this.name + " finished in " + duration + " ms");
        } catch (Exception e) {
            endTime = System.currentTimeMillis();
            duration = endTime - startTime;
            System.out.println("Ooooh. Looks like " + name + " is out of the race. It tripped over an " + e.getClass().getSimpleName()+ ". That's gotta hurt. ("+duration+"ms)");
        }
    }
}
