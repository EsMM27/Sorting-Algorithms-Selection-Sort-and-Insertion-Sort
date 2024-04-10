import java.util.*;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;

/**
   This program checks which words in a file are not present in a dictionary.
*/
public class SpellCheck
{
   public static void main(String[] args) 
      throws FileNotFoundException
   {
      // Read the dictionary and the document

    Set<String> dictionaryWords = readWords("Maps\\Dictionary.txt");
    Set<String> documentWords = readWords("Maps\\alice30.txt");
    Set<String> words = new HashSet<String>(); // Initialize the 'words' set

    // Print all words that are in the document but not the dictionary
    for (String word : documentWords)
    {
        if (!dictionaryWords.contains(word))
        {
            words.add(word);
        }
    }

    //q1
    //sort words
    List<String> sortedWords = new ArrayList<>(words);
    Collections.sort(sortedWords);

    for (String word : sortedWords)
    {
        //System.out.println(word);
    }

    //print a list of words with occurance in alice30.txt
   // Step 1: Determine how you access the values.
   // In our case, the values are the word frequencies. We have a frequency value for every word.
   // That is, we want to use a map that maps words to frequencies.
   Map<String, Integer> frequencies = new TreeMap<>();

   // Step 4: If you use a tree, decide whether to supply a comparator.
   // The key type for our tree map is String, which implements the Comparable interface.
   // Therefore, we need to do nothing further.

   // For each word in the input file
   for (String word : documentWords)
   {
      // Remove non-letters (such as punctuation marks) from the word.
      word = word.replaceAll("[^a-zA-Z]", "");

      // If the word is already present in the frequencies map
      if (frequencies.containsKey(word))
      {
         // Increment the frequency.
         frequencies.put(word, frequencies.get(word) + 1);
      }
      else
      {
         // Else, set the frequency to 1.
         frequencies.put(word, 1);
      }
   }

   // Print the words in sorted order along with their frequencies
   for (Map.Entry<String, Integer> entry : frequencies.entrySet())
   {
      System.out.println(entry.getKey() + "\t\t" + entry.getValue());

   }
    
    
    
   }

   /**
      Reads all words from a file.
      @param filename the name of the file
      @return a set with all lowercased words in the file. Here, a 
      word is a sequence of upper- and lowercase letters.
   */
   public static Set<String> readWords(String filename)
      throws FileNotFoundException
   {
      Set<String> words = new HashSet<String>();
      Scanner in = new Scanner(new File(filename));
      // Use any characters other than a-z or A-Z as delimiters
      in.useDelimiter("[^a-zA-Z]+");
      while (in.hasNext())
      {
         words.add(in.next().toLowerCase());        
      }
      return words;
   }
}