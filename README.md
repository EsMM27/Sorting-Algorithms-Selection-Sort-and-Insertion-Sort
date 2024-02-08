Sorting Algorithms – Selection Sort and Insertion Sort
Selection Sort and Insertion Sort are quadratic sorting algorithms that operate in the order of O(n2), The following practical will help you grasp how these algorithms are implemented and how the abstract concept of these sorting algorithms can differ from their concrete implementations. 

Consider the following array of Integer values.  

22	11	34	-5	3	40	9	16	6


1.Write the contents of the array for 5 passes of the outermost loop of insertion sort.
11	22	34	-5	3	40	9	16	6

11	22	34	-5	3	40	9	16	6

-5	11	22	34	3	40	9	16	6

-5	3	11	22	34	40	9	16	6

-5	3	11	22	34	40	9	16	6


2.Write the contents of the array for 4 passes of the outermost loop of selection sort.
-5	11	34	22	3	40	9	16	6

-5	3	34	22	11	40	9	16	6

-5	3	6	22	11	40	9	16	34

-5	3	6	9	11	40	22	16	34


3.Create a Selection Sort class that has a sorting method that will accept an array as an argument and will return a sorted array.
4.Create an Insertion Sort class that has a sorting method that will accept an array as an argument and will return a sorted array.
5.Write a piece of code that will propagate a static array of 10000 random elements.
6.Write a piece of code that will propagate a static array of 10000 sorted elements.
7.Write a piece of code that will propagate a static array of 10000 inversely sorted (sorted z – a).
8.Implement an array list, populating it similar to the arrays in 5, 6 and 7 above.
9.Rewrite the Insertion Sort and Selection Sort algorithms to sort the array lists.
10.Does changing the data structure from a static array to an array list have an impact on the overall efficiency of the algorithm?
11.Create a print method that will print both the initial array and the sorted version.
12.Write a print method that will validate your results in steps 1 and 2 above.
13.Write code that will record the number of comparisons that the Selection and Insertion Sort algorithms will carry out on the datasets implemented in steps 5, 6, and 7 above.
14.Write code that will record the number of swap operations that the Selection and Insertion Sort algorithms will carry out on the datasets implemented in steps 5, 6, and 7 above.


	
