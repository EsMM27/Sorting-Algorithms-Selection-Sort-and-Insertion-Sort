public class InsertionSort {
    public static void main(String[] args) {

        int array[] = {22, 11, 34, -5, 3, 40, 9, 16, 6};
        
        System.out.println("Original array:");
        printArray(array);
        
        int passes = insertionSort(array);

        System.out.println("Sorted array:");
        printArray(array);
        System.out.println("Number of passes: " + passes);
    }

    // Insertion sort algorithm
    private static int insertionSort(int[] array) {
        int passes = 0;
        // Iterate over the array starting from the second element
        for (int i = 1; i < array.length; i++) {
            // Store the current element to be inserted
            int key = array[i];
            // Initialize the index for comparison to the left of the current element
            int j = i - 1;
            
            // Move elements greater than the key to the right
            // j >= 0 ensures that the index does not go out of bounds

            // array[j] > key ensures that the elements are sorted in ascending order

            // array[j + 1] = array[j]; shifts the element to the right

            // j--; decrements the index to compare the next element

            //(j >= 0 && array[j] > key) is the condition to check if the element is greater than the key

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            
            // Insert the key into its correct position
            // array[j + 1] = key; inserts the key into its correct position
            array[j + 1] = key;
            passes++;
            System.out.println("Pass " + passes);
            printArray(array);
        }
        return passes;
    }

    // Utility method to print the array
    public static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}