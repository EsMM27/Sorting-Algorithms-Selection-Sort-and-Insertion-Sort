import java.util.Random;

public class StringGenerator {
    public static void main(String[] args) {
        int length = 10; // Change this to the desired length of the generated string
        String generatedString = generateString(length);
        System.out.println("Generated string: " + generatedString);
        
        String reversedString = reverseString(generatedString);
        System.out.println("Reversed string: " + reversedString);
    }
    
    public static String generateString(int length) {
        String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        
        for (int i = 0; i < length; i++) {
            // Generate a random index within the range of allowedCharacters length
            int randomIndex = random.nextInt(allowedCharacters.length());
            // Append the character at the random index to the string builder
            sb.append(allowedCharacters.charAt(randomIndex));
        }
        
        return sb.toString();
    }
    
    public static String reverseString(String str) {
        if (str.isEmpty()) {
            return str;
        } else {
            // Recursive call to reverse the substring starting from the second character
            return reverseString(str.substring(1)) + str.charAt(0);
        }
    }
}