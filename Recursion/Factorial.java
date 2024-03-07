public class Factorial {
    private static int depth = 0;
    
    public static void main(String[] args) {
        int number = 5; // Change this to the number you want to find factorial of
        long factorial = calculateFactorial(number, 0);
        System.out.println("Factorial of " + number + " is: " + factorial);
        System.out.println("Depth of recursion: " + depth);
    }
    
    public static long calculateFactorial(int n, int currentDepth) {
        if (currentDepth > depth) {
            depth = currentDepth;
        }
        
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * calculateFactorial(n - 1, currentDepth + 1);
        }
    }
}