import java.util.Arrays;

public class Two_Pivot_Block_Quicksort {

    // Utility method to swap elements in an array
    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    // Method to calculate block size
    private static int calculateBlockSize(int n) {
        double ratio = 10.0 / 26.0;
        double blockSize = Math.pow(2, ratio * Math.log(n) / Math.log(2));
        return (int) blockSize;
    }

    // Partition method for two-pivot block quicksort
    private static int[] twoPivotBlockPartition(int[] A, int low, int high, int blockSize) {
        int p = A[low]; // Left pivot
        int q = A[high]; // Right pivot
        int i = low + 1, j = low + 1, k = low + 1;
        int numLessP = 0, numLeqQ = 0;
        int[] block = new int[blockSize];

        while (k < high) {
            int t = Math.min(blockSize, high - k);
            for (int c = 0; c < t; c++) {
                block[numLeqQ] = c;
                numLeqQ += (q >= A[k + c]) ? 1 : 0;
            }
            for (int c = 0; c < numLeqQ; c++) {
                swap(A, j + c, k + block[c]);
            }
            k += t;
            for (int c = 0; c < numLeqQ; c++) {
                block[numLessP] = c;
                numLessP += (p > A[j + c]) ? 1 : 0;
            }
            for (int c = 0; c < numLessP; c++) {
                swap(A, i, j + block[c]);
                i++;
            }
            j += numLeqQ;
            numLessP = 0;
            numLeqQ = 0;
        }

        swap(A, low, i - 1); // Place the left pivot at its correct position
        swap(A, high, j); // Place the right pivot at its correct position
        return new int[]{i - 1, j};
    }

    // Recursive method for two-pivot block quicksort
    public static void twoPivotBlockQuickSort(int[] A, int low, int high) {
        if (high <= low) {
            return;
        }

        // Ensure the first pivot is less than the second pivot
        if (A[low] > A[high]) {
            swap(A, low, high);
        }

        int blockSize = calculateBlockSize(high - low + 1);
        int[] pivots = twoPivotBlockPartition(A, low, high, blockSize);
        int i = pivots[0], j = pivots[1];
        twoPivotBlockQuickSort(A, low, i - 1);
        twoPivotBlockQuickSort(A, i + 1, j - 1);
        twoPivotBlockQuickSort(A, j + 1, high);
    }

     public static void main(String[] args) {
        // Test cases
        int[][] sampleDatasets = {
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, // small_sorted
            {3, 7, 2, 8, 6, 0, 5, 9, 1, 4}, // small_random
            {9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, // small_reversed
            createRangeArray(20), // medium_sorted
            {11, 3, 16, 8, 0, 12, 14, 19, 7, 2, 15, 10, 1, 5, 18, 6, 9, 13, 4, 17}, // medium_random
            createReversedRangeArray(20), // medium_reversed
            createRangeArray(30), // large_sorted
            {27, 0, 8, 21, 15, 28, 12, 4, 10, 24, 19, 5, 26, 11, 3, 2, 7, 16, 23, 14, 18, 1, 25, 6, 20, 22, 9, 17, 29, 13}, // large_random
            createReversedRangeArray(30) // large_reversed
        };

        String[] labels = {
            "small_sorted", "small_random", "small_reversed",
            "medium_sorted", "medium_random", "medium_reversed",
            "large_sorted", "large_random", "large_reversed"
        };

        // Display original datasets and sort them
        for (int i = 0; i < sampleDatasets.length; i++) {
            System.out.println("Original " + labels[i] + ": " + Arrays.toString(sampleDatasets[i]));
            int[] sortedData = Arrays.copyOf(sampleDatasets[i], sampleDatasets[i].length);
            twoPivotBlockQuickSort(sortedData, 0, sortedData.length - 1);
            System.out.println("Sorted " + labels[i] + ": " + Arrays.toString(sortedData));
        }
    }

    // Helper method to create an array with a range of integers
    private static int[] createRangeArray(int size) {
        int[] range = new int[size];
        for (int i = 0; i < size; i++) {
            range[i] = i;
        }
        return range;
    }

    // Helper method to create a reversed array with a range of integers
    private static int[] createReversedRangeArray(int size) {
        int[] reversedRange = new int[size];
        for (int i = 0; i < size; i++) {
            reversedRange[i] = size - 1 - i;
        }
        return reversedRange;
    }
}