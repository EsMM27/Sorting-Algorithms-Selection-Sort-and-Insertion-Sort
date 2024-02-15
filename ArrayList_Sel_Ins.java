import java.util.ArrayList;

public class ArrayList_Sel_Ins {

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(22);
        arr.add(11);
        arr.add(34);
        arr.add(-5);
        arr.add(3);
        arr.add(40);
        arr.add(9);
        arr.add(16);
        arr.add(6);
        
        System.out.println("Original array:");
        printArrayList(arr);
        ArrayList<Integer> arr2 = new ArrayList<>(arr);
        
        int[] counts = selectionSort(arr);
        
        System.out.println("Sorted array:");
        printArrayList(arr);
        System.out.println("Number of comparisons: " + counts[0]);
        System.out.println("Number of swaps: " + counts[1]);
        System.out.println("Number of passes: " + counts[2]);

        long insertionSortTime = timeExecution(() -> insertionSort(arr));
        long SelectionSortTime = timeExecution(() -> selectionSort(arr2));
        System.out.println("Time taken to execute insertionSort: " + insertionSortTime + " nanoseconds");
        System.out.println("Time taken to execute insertionSort: " + SelectionSortTime + " nanoseconds");
    }

    public static int[] selectionSort(ArrayList<Integer> arr) {
        int n = arr.size();
        int comparisons = 0;
        int swaps = 0;
        int passes = 0;

        // One by one move the boundary of the unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            // Find the minimum element in the unsorted array
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr.get(j) < arr.get(minIndex)) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            if (minIndex != i) {
                int temp = arr.get(minIndex);
                arr.set(minIndex, arr.get(i));
                arr.set(i, temp);
                swaps++;
            }
            passes++;
        }
        return new int[]{comparisons, swaps, passes};
    }

    public static int[] insertionSort(ArrayList<Integer> arr) {
        int comparisons = 0;
        int swaps = 0;
        int passes = 0;

        for (int i = 1; i < arr.size(); i++) {
            int key = arr.get(i);
            int j = i - 1;

            while (j >= 0 && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j));
                j--;
                comparisons++;
                swaps++;
            }
            
            arr.set(j + 1, key);
            passes++;
        }
        return new int[]{comparisons, swaps, passes};
    }

    public static long timeExecution(Runnable method) {
        long startTime = System.nanoTime();
        method.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static void printArrayList(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();
    }
}