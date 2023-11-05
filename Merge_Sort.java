import java.util.Arrays;

public class Merge_Sort {
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

    public static void main(String[] args) {
        // Static sample datasets
        int[] small_sorted = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] small_random = {3, 7, 2, 8, 6, 0, 5, 9, 1, 4};
        int[] small_reversed = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] medium_sorted = new int[20];
        for (int i = 0; i < 20; i++) {
            medium_sorted[i] = i;
        }
        int[] medium_random = {11, 3, 16, 8, 0, 12, 14, 19, 7, 2, 15, 10, 1, 5, 18, 6, 9, 13, 4, 17};
        int[] medium_reversed = new int[20];
        for (int i = 0; i < 20; i++) {
            medium_reversed[i] = 19 - i;
        }
        int[] large_sorted = new int[30];
        for (int i = 0; i < 30; i++) {
            large_sorted[i] = i;
        }
        int[] large_random = {27, 0, 8, 21, 15, 28, 12, 4, 10, 24, 19, 5, 26, 11, 3, 2, 7, 16, 23, 14, 18, 1, 25, 6, 20, 22, 9, 17, 29, 13};
        int[] large_reversed = new int[30];
        for (int i = 0; i < 30; i++) {
            large_reversed[i] = 29 - i;
        }

        // Display original datasets
        System.out.println("Original small_sorted: " + Arrays.toString(small_sorted));
        System.out.println("Original small_random: " + Arrays.toString(small_random));
        System.out.println("Original small_reversed: " + Arrays.toString(small_reversed));
        System.out.println("Original medium_sorted: " + Arrays.toString(medium_sorted));
        System.out.println("Original medium_random: " + Arrays.toString(medium_random));
        System.out.println("Original medium_reversed: " + Arrays.toString(medium_reversed));
        System.out.println("Original large_sorted: " + Arrays.toString(large_sorted));
        System.out.println("Original large_random: " + Arrays.toString(large_random));
        System.out.println("Original large_reversed: " + Arrays.toString(large_reversed));

        // Apply mergeSort on datasets
        System.out.println("\nResults after mergeSort:");
        System.out.println("Sorted small_sorted: " + Arrays.toString(mergeSort(small_sorted.clone())));
        System.out.println("Sorted small_random: " + Arrays.toString(mergeSort(small_random.clone())));
        System.out.println("Sorted small_reversed: " + Arrays.toString(mergeSort(small_reversed.clone())));
        System.out.println("Sorted medium_sorted: " + Arrays.toString(mergeSort(medium_sorted.clone())));
        System.out.println("Sorted medium_random: " + Arrays.toString(mergeSort(medium_random.clone())));
        System.out.println("Sorted medium_reversed: " + Arrays.toString(mergeSort(medium_reversed.clone())));
        System.out.println("Sorted large_sorted: " + Arrays.toString(mergeSort(large_sorted.clone())));
        System.out.println("Sorted large_random: " + Arrays.toString(mergeSort(large_random.clone())));
        System.out.println("Sorted large_reversed: " + Arrays.toString(mergeSort(large_reversed.clone())));
    }
}