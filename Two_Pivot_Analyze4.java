import java.util.Arrays;
import java.util.Random;

public class Two_Pivot_Analyze4 {
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
        if (blockSize < A.length) {
            int[] pivots = twoPivotBlockPartition(A, low, high, blockSize);
            int i = pivots[0], j = pivots[1];
            twoPivotBlockQuickSort(A, low, i - 1);
            twoPivotBlockQuickSort(A, i + 1, j - 1);
            twoPivotBlockQuickSort(A, j + 1, high);
        } else {
            // For very large datasets, use Arrays.sort to sort the subarray
            Arrays.sort(A, low, high + 1);
        }
    }

    // Method to create a sorted array
    private static int[] createSortedArray(int size) {
        int[] sortedArray = new int[size];
        for (int i = 0; i < size; i++) {
            sortedArray[i] = i;
        }
        return sortedArray;
    }

    // Method to create a reversed array
    private static int[] createReversedArray(int size) {
        int[] reversedArray = new int[size];
        for (int i = 0; i < size; i++) {
            reversedArray[i] = size - 1 - i;
        }
        return reversedArray;
    }

    // Method to create a random array
    private static int[] createRandomArray(int size) {
        Random rand = new Random();
        int[] randomArray = new int[size];
        for (int i = 0; i < size; i++) {
            randomArray[i] = rand.nextInt();
        }
        return randomArray;
    }

    public static void main(String[] args) {
        // Sizes for small, medium, and large datasets
        // int smallSize = (int) Math.pow(2, 9);
        int mediumSize = (int) Math.pow(2, 13);
        // int largeSize = (int) Math.pow(2, 16);

        // Generate datasets
        int[][] datasets = {
            // createSortedArray(smallSize),
            // createRandomArray(smallSize),
            // createReversedArray(smallSize),
            createSortedArray(mediumSize),
            // createRandomArray(mediumSize),
            // createReversedArray(mediumSize),
            // createSortedArray(largeSize),
            // createRandomArray(largeSize),
            // createReversedArray(largeSize)
        };

        String[] labels = {
            // "small_sorted"
        //    "small_random"
            // "small_reversed"
            "medium_sorted"
            //  "medium_random", "medium_reversed",
            // "large_sorted", "large_random", 
            // "large_reversed"
        };

        // Perform sorting and measure memory and time
        for (int i = 0; i < datasets.length; i++) {
            try {
                System.out.println("Sorting " + labels[i]);

                // Measure memory before sorting
                long memoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

                // Measure time before sorting
                long startTime = System.currentTimeMillis();

                // Sort the dataset
                twoPivotBlockQuickSort(datasets[i], 0, datasets[i].length - 1);

                // Measure time after sorting
                long endTime = System.currentTimeMillis();

                // Measure memory after sorting
                long memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

                // Calculate memory used and time taken
                long memoryUsed = memoryAfter - memoryBefore;
                long timeTaken = endTime - startTime;

                System.out.println("Memory used: " + memoryUsed + " bytes");
                System.out.println("Time taken: " + timeTaken + " ms\n");
            } catch (StackOverflowError error) {
                System.out.println("Sorting " + labels[i] + " failed: Stack overflow error. Dataset may be too large for recursive quicksort.");
            }
        }
    }
}
