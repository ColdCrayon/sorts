package edu.grinnell.csc207.sorts;

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
        // TODO: fill me in!
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
        // TODO: fill me in!
    }
}
