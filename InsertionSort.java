public class InsertionSort {
    public static void main(String[] args) {
        int array[] = {22, 11, 34, -5, 3, 40, 9, 16, 6};
        
        System.out.println("Original array:");
        printArray(array);
        
        int[] result = insertionSort(array);
        int passes = result[0];
        int comparisons = result[1];
        int swaps = result[2];

        System.out.println("Sorted array:");
        printArray(array);
        System.out.println("Number of passes: " + passes);
        System.out.println("Number of comparisons: " + comparisons);
        System.out.println("Number of swaps: " + swaps);
    }

    private static int[] insertionSort(int[] array) {
        int passes = 0;
        int comparisons = 0;
        int swaps = 0;
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            //if (array[j] > key)  // Compare the current element with the key
            if (array[j] > key) {
                swaps++; // Increment swap count
            }

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
                comparisons++; // Increment comparison count
            }

            array[j + 1] = key;
            passes++;
        }
        int[] result = {passes, comparisons, swaps};
        return result;
    }

    public static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}