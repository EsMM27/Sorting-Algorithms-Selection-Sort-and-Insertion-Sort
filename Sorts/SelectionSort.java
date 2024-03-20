public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {22, 11, 34, -5, 3, 40, 9, 16, 6};
        
        System.out.println("Original array:");
        printArray(arr);
        
        int[] counts = selectionSort(arr);
        
        System.out.println("Sorted array:");
        printArray(arr);
        System.out.println("Number of comparisons: " + counts[0]);
        System.out.println("Number of swaps: " + counts[1]);
        System.out.println("Number of passes: " + counts[2]);
    }

    public static int[] selectionSort(int[] arr) {
        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;
        int passes = 0;

        // One by one move the boundary of the unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            // Find the minimum element in the unsorted array
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
                swaps++;
            }
            passes++;
        }
        return new int[]{comparisons, swaps, passes};
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}