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
        algorithm.accept(data);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Thread " + this.name + " finished in " + duration + " ms");
    }
}
