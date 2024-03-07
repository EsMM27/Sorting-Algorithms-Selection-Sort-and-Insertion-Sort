public class PalindromeChecker {
    public static void main(String[] args) {
        //String str = "radar"; // Change this to the string you want to check

        String generatedString = StringGenerator.generateString(6);

        boolean result = isPalindrome(generatedString);
        if (result) {
            System.out.println(generatedString + " is a palindrome.");
        } else {
            System.out.println(generatedString + " is not a palindrome.");
        }
    }
    
    public static boolean isPalindrome(String str) {
        // Convert the string to lowercase to handle case-insensitivity
        str = str.toLowerCase();
        
        int left = 0;
        int right = str.length() - 1;
        
        // Iterate until left pointer is less than or equal to right pointer
        while (left < right) {
            // Ignore non-alphanumeric characters from the left
            while (left < right && !Character.isLetterOrDigit(str.charAt(left))) {
                left++;
            }
            // Ignore non-alphanumeric characters from the right
            while (left < right && !Character.isLetterOrDigit(str.charAt(right))) {
                right--;
            }
            // Compare characters at left and right pointers
            if (str.charAt(left) != str.charAt(right)) {
                return false; // Characters don't match, not a palindrome
            }
            left++;
            right--;
        }
        return true; // All characters match, it's a palindrome
    }
}