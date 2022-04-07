import algorithm.ElementarySorting;
import task.BenchmarkAlgorithm;
import task.ThreadRace;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random rand = new Random();
        int size = 10000;
        var arr = generateRandomInts(size, rand);
        //var arr = generateSequentialData(size, true);
        //var arr = generateSequentialData(size, false);
        var arr2 = arr.clone();
        var arr3 = arr.clone();
        var arr4 = arr.clone();
        var arr5 = arr.clone();
        var arr6 = arr.clone();
        ElementarySorting<Integer> sorting = new ElementarySorting<>();

        Thread selectionSort = new Thread(new BenchmarkAlgorithm<Integer>("Selection Sort", sorting::selectionSort, arr));
        selectionSort.setName("Selection Sort");
        Thread insertionSort = new Thread(new BenchmarkAlgorithm<Integer>("Insertion Sort", sorting::insertionSort, arr2));
        insertionSort.setName("Insertion Sort");
        Thread bubbleSort = new Thread(new BenchmarkAlgorithm<Integer>("Bubble Sort", sorting::bubbleSort, arr3));
        bubbleSort.setName("Bubble Sort");
        Thread shellSort = new Thread(new BenchmarkAlgorithm<Integer>("Shell Sort", sorting::shellSort, arr4));
        shellSort.setName("Shell Sort");
        Thread quicksortiterative = new Thread(new BenchmarkAlgorithm<Integer>("Quick Sort Iterator", sorting::quickSortIterative, arr5));
        quicksortiterative.setName("Quick Sort");
        Thread quicksortrecursive = new Thread(new BenchmarkAlgorithm<Integer>("Quick Sort Recursive", sorting::quickSortRecursive, arr6));
        quicksortrecursive.setName("Quick Sort Recursive");
        Thread radixsort = new Thread(new BenchmarkAlgorithm<Integer>("Radix Sort", sorting::radixSort, arr));
        radixsort.setName("Radix Sort");



        Thread race = new Thread(new ThreadRace(selectionSort,insertionSort,bubbleSort,shellSort, quicksortiterative, quicksortrecursive));

        System.out.println("Let's benchmark the performance of some algorithms Java language!");
        System.out.println("The array size is " + size + " elements");
        race.start();
    }

    private static Integer[] generateRandomInts(int size, Random random) {
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size);
        }
        return arr;
    }

    private static Integer[] generateSequentialData(int size, boolean ascending) {
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            arr[i] = ascending ? i : size - i;
        }
        return arr;
    }
}
