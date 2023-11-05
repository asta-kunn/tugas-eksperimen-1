import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class Merge_Sort_Analyze1 {
    // Merge Sort Implementation
    public static int[] mergeSort(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;
            int[] L = Arrays.copyOfRange(arr, 0, mid);
            int[] R = Arrays.copyOfRange(arr, mid, arr.length);

            mergeSort(L);
            mergeSort(R);

            int i = 0, j = 0, k = 0;

            while (i < L.length && j < R.length) {
                if (L[i] < R[j]) {
                    arr[k] = L[i];
                    i++;
                } else {
                    arr[k] = R[j];
                    j++;
                }
                k++;
            }

            while (i < L.length) {
                arr[k] = L[i];
                i++;
                k++;
            }

            while (j < R.length) {
                arr[k] = R[j];
                j++;
                k++;
            }
        }
        return arr;
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
            randomArray[i] = rand.nextInt(size);
        }
        return randomArray;
    }

    public static void main(String[] args) {
        // Sizes for small, medium, and large datasets
        int smallSize = (int) Math.pow(2, 9);
        // int mediumSize = (int) Math.pow(2, 13);
        // int largeSize = (int) Math.pow(2, 16);

          // Generate datasets
          int[][] datasets = {
            createSortedArray(smallSize),
            // createRandomArray(smallSize),
            // createReversedArray(smallSize),
            // createSortedArray(mediumSize),
            // createRandomArray(mediumSize),
            // createReversedArray(mediumSize),
            // createSortedArray(largeSize),
            // createRandomArray(largeSize),
            // createReversedArray(largeSize)
        };

        String[] labels = {
            "small_sorted"
            // , "small_random", "small_reversed",
            // "medium_sorted", "medium_random", "medium_reversed",
            // "large_sorted", "large_random", 
            // "large_reversed"
        };
        for (int i = 0; i < datasets.length; i++) {
            try {
                System.out.println("Sorting " + labels[i]);

                // Measure memory before sorting
                long memoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

                // Measure time before sorting
                long startTime = System.currentTimeMillis();

                // Sort the dataset using merge sort
                mergeSort(datasets[i].clone());

                // Measure time after sorting
                long endTime = System.currentTimeMillis();

                // Measure memory after sorting
                long memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

                // Calculate memory used and time taken
                long memoryUsed = memoryAfter - memoryBefore;
                long timeTaken = endTime - startTime;

                DecimalFormat decimalFormat = new DecimalFormat("#.####");
                String formattedMemoryUsed = decimalFormat.format(memoryUsed / 1024.0); // Convert bytes to kilobytes

                System.out.println("Memory used: " + formattedMemoryUsed + " bytes");
                System.out.println("Time taken: " + timeTaken + " ms\n");
            } catch (StackOverflowError error) {
                System.out.println("Sorting " + labels[i] + " failed: Stack overflow error.");
            }
        }

    
    }
}
