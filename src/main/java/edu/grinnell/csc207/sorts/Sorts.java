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

    public static <T extends Comparable<? super T>> void mergeHelper(T[] arr, T[] shadow) {
        if (arr.length == 2) {
            if (arr[0].compareTo(arr[1]) > 0) {
                swap(arr, 0, 1);
            }
        }

        T[] arr1 = Arrays.copyOfRange(arr, 0, arr.length / 2);
        T[] arr2 = Arrays.copyOfRange(arr, arr.length / 2, arr.length);

        mergeHelper(arr1, shadow);
        mergeHelper(arr2, shadow);

        int index1 = 0;
        int index2 = 0;
        int i = 0;
        for (; i < shadow.length && index1 < arr1.length && index2 < arr2.length; i++) {
            if (arr1[index1].compareTo(arr2[index2]) < 0) {
                shadow[i] = arr1[index1];
                index1++;
            } else {
                shadow[i] = arr2[index2];
                index2++;
            }
        }

        if (index1 < arr1.length) {
            for (int j = index1; j < arr1.length; j++) {
                shadow[i] = arr1[j];
                i++;
            }
        } else if (index2 < arr2.length) {
            for (int j = index2; j < arr2.length; j++) {
                shadow[i] = arr2[j];
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
        T[] shadow = Arrays.copyOf(arr, arr.length);

        mergeHelper(arr, shadow);
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
