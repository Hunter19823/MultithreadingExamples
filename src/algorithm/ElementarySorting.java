package algorithm;

import java.util.Stack;

public class ElementarySorting<DATA extends Comparable<DATA>> {
    // Selection sort that sorts an array of DATA[] in ascending order
    public void selectionSort(DATA[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].compareTo(arr[min]) < 0) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    // Insertion sort that sorts an array of DATA[] in ascending order
    public void insertionSort(DATA[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
                swap(arr, j, j - 1);
            }
        }
    }

    // Bubble sort that sorts an array of DATA[] in ascending order
    public void bubbleSort(DATA[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    // Quick sort that sorts an array of DATA[] in ascending order iteratively
    public void quickSortIterative(DATA[] arr) {
        quickSortIterative(arr, 0, arr.length - 1);
    }

    private void quickSortIterative(DATA[] arr, int low, int high) {
        Stack<Integer[]> stack = new Stack<>();
        stack.push(new Integer[]{low, high});
        while (!stack.isEmpty()) {
            Integer[] cur = stack.pop();
            int pivot = partition(arr, cur[0], cur[1]);
            if (pivot - 1 > cur[0]) {
                stack.push(new Integer[]{cur[0], pivot - 1});
            }
            if (pivot + 1 < cur[1]) {
                stack.push(new Integer[]{pivot + 1, cur[1]});
            }
        }
    }

    // Quick Sort that sorts an array of DATA[] in ascending order recursively
    public void quickSortRecursive(DATA[] arr) {
        quickSortRecursive(arr, 0, arr.length - 1);
    }

    private void quickSortRecursive(DATA[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            quickSortRecursive(arr, low, pivot - 1);
            quickSortRecursive(arr, pivot + 1, high);
        }
    }

    private int partition(DATA[] arr, int low, int high) {
        DATA pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) < 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    // Shell sort that sorts an array of DATA[] in ascending order
    public void shellSort(DATA[] arr) {
        int n = arr.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && arr[j].compareTo(arr[j - h]) < 0; j -= h) {
                    swap(arr, j, j - h);
                }
            }
            h = h / 3;
        }
    }


    public void swap(DATA[] arr, int i, int j) {
        DATA temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Radix sort that sorts an array of DATA[] in ascending order
    public void radixSort(Integer[] arr) {
        int n = arr.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i].compareTo(arr[max]) > 0) {
                max = i;
            }
        }
        int maxDigit = getDigit(arr[max], 0);
        for (int digit = 1; digit <= maxDigit; digit++) {
            countingSort(arr, digit);
        }
    }

    private int getDigit(Integer data, int digit) {
        int num = data;
        int pow = (int) Math.pow(10, digit);
        return (num / pow) % 10;
    }

    private void countingSort(Integer[] arr, int digit) {
        int n = arr.length;
        int[] count = new int[10];
        for (int i = 0; i < n; i++) {
            count[getDigit(arr[i], digit)]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        Integer[] output = new Integer[n];
        for (int i = n - 1; i >= 0; i--) {
            output[count[getDigit(arr[i], digit)] - 1] = arr[i];
            count[getDigit(arr[i], digit)]--;
        }
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // Heap sort that sorts an array of DATA[] in ascending order
    public void heapSort(DATA[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    private void heapify(DATA[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && arr[l].compareTo(arr[largest]) > 0) {
            largest = l;
        }
        if (r < n && arr[r].compareTo(arr[largest]) > 0) {
            largest = r;
        }
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    // Merge sort that sorts an array of DATA[] in ascending order
    public void mergeSort(DATA[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(DATA[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    private void merge(DATA[] arr, int low, int mid, int high) {
        Comparable<DATA>[] temp = new Comparable[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high) {
            if (arr[i].compareTo(arr[j]) < 0) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= high) {
            temp[k++] = arr[j++];
        }
        for (i = 0; i < temp.length; i++) {
            arr[low + i] = (DATA) temp[i];
        }
    }
}
