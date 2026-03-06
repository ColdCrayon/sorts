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
        if (upper - lower == 2) {
            if (arr[lower].compareTo(arr[upper - 1]) > 0) {
                shadow[lower] = arr[upper - 1];
                shadow[upper - 1] = arr[lower];
            } else {
                shadow[upper - 1] = arr[upper - 1];
                shadow[lower] = arr[lower];
            }
            return;
        } else if (upper - lower == 1) {
            shadow[lower] = arr[lower];
            return;
        }

        mergeHelper(arr, shadow, 0, (((upper - lower) / 2) + lower));
        mergeHelper(arr, shadow, (((upper - lower) / 2) + lower), upper);

        int index1 = lower;
        int index2 = ((upper - lower) / 2) + lower;
        int i = lower;
        for (; i < upper && index1 < (((upper - lower) / 2) + lower) && index2 < upper; i++) {
            if (arr[index1].compareTo(arr[index2]) < 0) {
                shadow[i] = arr[index1];
                index1++;
            } else {
                shadow[i] = arr[index2];
                index2++;
            }
        }

        if (index1 < (((upper - lower) / 2) + lower)) {
            for (int j = index1; j < (((upper - lower) / 2) + lower); j++) {
                shadow[i] = arr[j];
                i++;
            }
        } else if (index2 < upper) {
            for (int j = index2; j < upper; j++) {
                shadow[i] = arr[j];
                i++;
            }
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
        System.out.println(Arrays.toString(arr));
        T[] shadow = Arrays.copyOf(arr, arr.length);

        mergeHelper(arr, shadow, 0, arr.length);
        System.out.println(Arrays.toString(shadow));
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

    }
}
