package homework1;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Stack;

public class Sort {

    /**
     * Quicksort. Given array is mutated to a sorted state.
     *
     * @param x List of Comparable values to be sorted.
     */
    public static <T extends Comparable<? super T>> void quicksort(List<T> x) throws IndexOutOfBoundsException {
        if (x.isEmpty()) return;

        int n = x.size();
        // Use a stack to store indices
        Stack<Integer> stack = new Stack();

        int p = 0;
        int r = n - 1;
        // Push initial indices
        stack.push(p);
        stack.push(r);

        // Sort the array using quick sort
        while (!stack.empty()) {
            r = stack.pop();
            p = stack.pop();
            // Utility function to partition list
            int q = partition(x, p, r);

            // Push indices for left half
            if (q - 1 > p) {
                stack.push(p);
                stack.push(q - 1);
            }
            // Push indices for right half
            if (q + 1 < r) {
                stack.push(q + 1);
                stack.push(r);
            }
        }
    }

    /**
     * Mergesort. The given array is mutated to a sorted state.
     *
     * @param x List of comparable values.
     */
    public static <T extends Comparable<? super T>> void mergesort(List<T> x) throws IndexOutOfBoundsException {
        if (x.isEmpty()) return;

        int n = x.size();

        // Sort the array using merge sort
        if (n > 1) {
            int mid = n / 2;
            // Left half
            ArrayList<T> left = new ArrayList(mid);
            for (int i = 0; i < mid; i++) {
                left.add(x.get(i));
            }
            // Right half
            ArrayList<T> right = new ArrayList(n - mid);
            for (int i = mid; i < n; i++) {
                right.add(x.get(i));
            }
            // Recursive calls until n <= 1
            mergesort(left);
            mergesort(right);
            // Utility function to merge both halves together
            merge(x, left, right);
        }
    }

    /**
     * Insertionsort. The given array is mutated to a sorted state.
     *
     * @param x a List of Comparable values.
     */
    public static <T extends Comparable<? super T>> void insertionsort(List<T> x) throws IndexOutOfBoundsException {
        if (x.isEmpty()) return;

        int n = x.size();

        // Dump values of x into an array
        ArrayList<T> vals = new ArrayList(x);
        // Sort the array using insertion sort
        for (int j = 1; j < n; j++) {
            var key = vals.get(j);
            int i = j - 1;
            while ((i >= 0) && (vals.get(i).compareTo(key) > 0)) {
                vals.set(i + 1, vals.get(i));
                i = i - 1;
            }
            vals.set(i + 1, key);
        }
        // Copy values back into x
        copyTo(vals, x);
    }

    /**
     * Selectionsort. The given array is mutated to a sorted state.
     *
     * @param x a List of Comparable values.
     */
    public static <T extends Comparable<? super T>> void selectionsort(List<T> x) throws IndexOutOfBoundsException {
        if (x.isEmpty()) return;

        int n = x.size();

        // Sort the array using selection sort
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (x.get(j).compareTo(x.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
        swap(x, minIndex, i);
        }
    }

    /**
     * Heapsort. The given array is mutated to a sorted state.
     *
     * @param x List of Comparable values.
     */
    public static <T extends Comparable<? super T>> void heapsort(List<T> x) throws IndexOutOfBoundsException {
        if (x.isEmpty()) return;

        int n = x.size();

        // Build heap
        for (int i = (n / 2) - 1; i >= 0; i--) {
            maxHeapify(x, i, n);
        }
        // Sort the array using heap sort
        for (int i = n - 1; i >= 0; i--) {
            swap(x, 0, i);
            maxHeapify(x, 0, i);
        }
    }

    /**
     * Bubblesort. The given array is mutated to a sorted state.
     *
     * @param x a List of comparable values.
     */
    public static <T extends Comparable<? super T>> void bubblesort(List<T> x) throws IndexOutOfBoundsException {
        if (x.isEmpty()) return;

        // Dump values of x into an array
        ArrayList<T> vals = new ArrayList(x);

        // Sort the array using bubble sort
        int lastSwapped = vals.size();
        while (lastSwapped > 0) {
            int currentSwapped = 0;
            for (int i = 0; i < vals.size()-1; i++) {
                // Use compareTo to compare values
                if (vals.get(i).compareTo(vals.get(i+1)) > 0) {
                    var tmp = vals.get(i);
                    vals.set(i, vals.get(i+1));
                    vals.set(i+1, tmp);
                    currentSwapped = i;
                }
            }
            lastSwapped = currentSwapped;
        }
        // Utility functoin to copy values from ArrayList vals to List x in linear time
        copyTo(vals, x);
    }

    /**
     * Counting sort. The given array is mutated to a sorted state.
     *
     * @param x List<Integer> of numbers.
     */
    public static void countingsort(List<Integer> x) throws IndexOutOfBoundsException {
        if (x.isEmpty()) return;

        int n = x.size();

        // Dump values of x into an array
        ArrayList<Integer> vals = new ArrayList(x);
        // Find min and max values within x
        int min = x.get(0);
        int max = x.get(0);
        for (int i = 0; i < n; i++) {
            if (x.get(i).compareTo(min) < 0) {
                min = x.get(i);
            } else if (x.get(i).compareTo(max) > 0) {
                max = x.get(i);
            }
        }
        // Build count array
        int[] c = new int[max - min + 1];
        for (int i = 0; i < n; i++) {
            c[x.get(i) - min]++;
        }
        for(int i = 1; i < c.length; i++) {
            c[i] += c[i - 1];
        }
        // Build output array
        for (int i = n - 1; i >= 0; i--) {
            vals.set(c[x.get(i) - min] - 1, x.get(i));
            c[x.get(i) - min]--;
        }
        // Copy values back into x
        copyTo(vals, x);
    }

    /**
     * Bucketsort - assumes x is a List<Double> whose values are uniformly distributed on [0,1). The given array is mutated
     * to a sorted state.
     *
     * @param x List<Double> of uniformly sampled values from U(0,1).
     */
    public static void bucketsort(List<Double> x) throws IndexOutOfBoundsException {
        if (x.isEmpty()) return;

        int n = x.size();

        // Create buckets (array of ArrayList)
        ArrayList[] b = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            b[i] = new ArrayList<Double>();
        }
        // Populate buckets with respective values
        for (int i = 0; i < n; i++) {
            b[(int)(n * x.get(i))].add(x.get(i));
        }
        // Sort buckets using insertion sort
        for (int i = 0; i < n; i++) {
            insertionsort(b[i]);
        }
        // Concatenate the buckets back into x
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < b[i].size(); j++) {
                x.set(index, (double)(b[i].get(j)));
                index++;
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // UTILITY FUNCTIONS
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Partitions a list
     *
     * @param x a list containing values
     * @param p start index
     * @param r end index
     * @return partition index j
     */
    public static <T extends Comparable<? super T>> int partition(List<T> x, int p, int r) throws IndexOutOfBoundsException {
        var k = x.get(r);
        int j = p;

        for (int i = p; i < r; i++) {
            if(x.get(i).compareTo(k) < 0) {
                swap(x, i, j);
                j++;
            }
        }
        swap(x, j, r);
        return j;
    }

    /**
     * Merges two lists together into a sorted list
     *
     * @param x a list containing values
     * @param l left half
     * @param r right half
     */
    public static <T extends Comparable<? super T>> void merge(List<T> x, List<T> l, List<T> r) throws IndexOutOfBoundsException {
        int i = 0, j = 0, k = 0;
        int nl = l.size();
        int nr = r.size();

        // Merge halves
        while (i < nl && j < nr) {
            if (l.get(i).compareTo(r.get(j)) < 0) {
                x.set(k, l.get(i));
                i++;
            } else {
                x.set(k, r.get(j));
                j++;
            }
            k++;
        }
        // Deal with remaining elements (if any)
        while (i < nl) {
            x.set(k, l.get(i));
            i++;
            k++;
        }
        while (j < nr) {
            x.set(k, r.get(j));
            j++;
            k++;
        }
    }

    /**
     * Modifies a list to maintain the max heap property rooted at index i
     *
     * @param x a list containing values
     * @param i root index
     */
    public static <T extends Comparable<? super T>> void maxHeapify(List<T> x, int i, int n) throws IndexOutOfBoundsException {
        var l = (i * 2) + 1;
        var r = (i * 2) + 2;
        int largest = i;

        if (l < n && x.get(l).compareTo(x.get(largest)) > 0) {
            largest = l;
        } else {
            largest = i;
        }
        if (r < n && x.get(r).compareTo(x.get(largest)) > 0) {
            largest = r;
        }
        if (largest != i) {
            swap(x, i, largest);
            maxHeapify(x, largest, n);
        }
    }


        /**
         * Swaps two elements in a List<T>
         *
         * @param x a list containing values
         * @param a first index to swap
         * @param b second index to swap
         */
    public static <T extends Comparable<? super T>> void swap(List<T> x, int a, int b) throws IndexOutOfBoundsException {
        var temp = x.get(a);
        x.set(a, x.get(b));
        x.set(b, temp);
    }

    /**
     * Checks if the given list is sorted in linear time.
     *
     * @param l a list of Comparable values.
     * @return True if the list is sorted, False otherwise.
     */
    public static <T extends Comparable<? super T>> boolean isSorted(List<T> l) throws IndexOutOfBoundsException {
    if (l.isEmpty() || l.size() == 1) return true;
    T x = l.get(0);
    for (int i = 1; i < l.size(); i++) {
        T y = l.get(i);
        if (x.compareTo(y) > 0) return false;
        x = y;
    }
    return true;
    }

    /**
     * Copies the contents of an ArrayList to a List in linear time.
     *
     * @param x an ArrayList that is copied from.
     * @param y a List that is copied to.
     */
    private static <T> void copyTo(ArrayList<T> x, List<T> y) {
    ListIterator<T> li = y.listIterator();
    while (li.hasNext()) {
        li.next();
        if (li.previousIndex() < x.size())
        li.set(x.get(li.previousIndex()));
    }
    }
}
