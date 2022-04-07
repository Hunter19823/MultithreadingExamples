package task;

import java.util.function.Consumer;

public class SortOneDimensionalTask<DATA extends Comparable<DATA>> implements Runnable {
    public final Consumer<DATA[]> algorithm;
    public final DATA[] data;

    public SortOneDimensionalTask(Consumer<DATA[]> algorithm, DATA[] data) {
        this.algorithm = algorithm;
        this.data = data;
    }

    @Override
    public void run() {
        algorithm.accept(data);
    }
}
