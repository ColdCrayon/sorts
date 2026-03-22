
package edu.grinnell.csc207.sorts;

import java.util.Arrays;

/**
 * A collection of sorting algorithms over generic arrays.
 */
public class Sorts {
    /**
     * Swaps indices <code>i</code> and <code>j</code> of array <code>arr</code>.
     * 
     * @param <T> the carrier type of the array
     * @param arr the array to swap
     * @param i   the first index to swap
     * @param j   the second index to swap
     */
    public static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * Sorts the array according to the bubble sort algorithm:
     * 
     * <pre>
     * [ unprocessed | i largest elements in order ]
     * </pre>
     * 
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] arr) {
        int maxIndex = 0;
        int stopper = arr.length;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < stopper; j++) {
                if (arr[maxIndex].compareTo(arr[j]) < 0) {
                    maxIndex = j;
                }
            }
            swap(arr, maxIndex, stopper - 1);
            maxIndex = 0;
            stopper--;
        }

    }

    /**
     * Sorts the array according to the selection sort algorithm:
     * 
     * <pre>
     * [ i smallest elements in order | unprocessed ]
     * </pre>
     * 
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void selectionSort(T[] arr) {
        int minIndex = arr.length - 1;
        int stopper = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = arr.length - 1; j >= stopper; j--) {
                if (arr[minIndex].compareTo(arr[j]) > 0) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, stopper);
            minIndex = arr.length - 1;
            stopper++;
        }
    }

    /**
     * Sorts the array according to the insertion sort algorithm:
     * 
     * <pre>
     * [ i elements in order | unprocessed ]
     * </pre>
     * 
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] arr) {
        int stopper = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = stopper; j > 0; j--) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    swap(arr, j, j - 1);
                }
            }
            stopper++;
        }
    }

    public static <T extends Comparable<? super T>> void mergeHelper(T[] arr, T[] shadow, int lower, int upper) {

        if (upper - lower <= 1) {
            if (upper - lower == 1) {
                shadow[lower] = arr[lower];
            }
            return;
        } else if (upper - lower == 2) {
            if (arr[lower].compareTo(arr[upper - 1]) > 0) {
                shadow[lower] = arr[upper - 1];
                shadow[upper - 1] = arr[lower];
            } else {
                shadow[upper - 1] = arr[upper - 1];
                shadow[lower] = arr[lower];
            }
            arr[lower] = shadow[lower];
            arr[upper - 1] = shadow[upper - 1];
            return;
        }

        int mid = ((upper - lower) / 2) + lower;

        mergeHelper(arr, shadow, lower, mid);
        mergeHelper(arr, shadow, mid, upper);

        int index1 = lower;
        int index2 = mid;
        int i = lower;
        for (; i < upper && index1 < mid && index2 < upper; i++) {
            if (arr[index1].compareTo(arr[index2]) < 0) {
                shadow[i] = arr[index1];
                index1++;
            } else {
                shadow[i] = arr[index2];
                index2++;
            }
        }

        if (index1 < mid) {
            for (int j = index1; j < mid; j++) {
                shadow[i] = arr[j];
                i++;
            }
        } else if (index2 < upper) {
            for (int j = index2; j < upper; j++) {
                shadow[i] = arr[j];
                i++;
            }
        }

        for (int j = lower; j < upper; j++) {
            arr[j] = shadow[j];
    }

    }

    /**
     * Sorts the array according to the merge sort algorithm:
     * <ol>
     * <li>Sort the left half of the array recursively.</li>
     * <li>Sort the right half of the array recursively.</li>
     * <li>Merge the two sorted halves into a sorted whole.</li>
     * </ol>
     * 
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] arr) {
        T[] shadow = Arrays.copyOf(arr, arr.length);

        mergeHelper(arr, shadow, 0, arr.length);
    }

    public static <T extends Comparable<? super T>> void quickHelper(T[] arr, int lower, int upper) {
        //check base case fr
        if (upper - lower <= 1) {
            return;
        }
        int mid = ((upper - lower) / 2) + lower; //find midpoint
        if ((arr[mid].compareTo(arr[lower]) < 0 && arr[lower].compareTo(arr[upper - 1]) < 0) ||
            (arr[upper - 1].compareTo(arr[lower]) < 0 && arr[lower].compareTo(arr[mid]) < 0)){ //if lower is median, switch lower to last element
            T temp = arr[lower];
            arr[lower] = arr[upper - 1];
            arr[upper - 1] = temp;
        } else if ((arr[lower].compareTo(arr[mid]) < 0 && arr[mid].compareTo(arr[upper - 1]) < 0) ||
                   (arr[upper - 1].compareTo(arr[mid]) < 0 && arr[mid].compareTo(arr[lower]) < 0)){ //if mid is median, switch mid to last element
            T temp = arr[mid];
            arr[mid] = arr[upper - 1];
            arr[upper - 1] = temp;
        }
        int boundary = lower; //boundary of sorted region
        for (int i = lower; i < upper - 1; i++){ //sweep.
            if (arr[i].compareTo(arr[upper - 1]) <= 0){ //if element is less than pivot, move it to the boundary region.
                T temp = arr[boundary];
                arr[boundary] = arr[i];
                arr[i] = temp;
                boundary++;
            }
        }
        T temp = arr[boundary];
        arr[boundary] = arr[upper - 1];
        arr[upper - 1] = temp;
        quickHelper(arr, lower, boundary);
        quickHelper(arr, boundary + 1, upper);
    }


    /**
     * Sorts the array according to the quick sort algorithm:
     * <ol>
     * <li>Choose a pivot value and partition the array according to the pivot.</li>
     * <li>Sort the left half of the array recursively.</li>
     * <li>Sort the right half of the array recursively.</li>
     * </ol>
     * 
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] arr) {
        quickHelper(arr, 0, arr.length);
    }
}
