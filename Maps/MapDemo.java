import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
   This program demonstrates a map that maps names to colours.
*/
public class MapDemo
{
   public static void main(String[] args)
   {      
      Map<String, Color> favoriteColours = new HashMap<String, Color>();
      favoriteColours.put("Juliet", Color.BLUE);
      favoriteColours.put("Romeo", Color.GREEN);
      favoriteColours.put("Adam", Color.RED);
      favoriteColours.put("Eve", Color.BLUE);

      // Print all keys and values in the map

      Set<String> keySet = favoriteColours.keySet();
      for (String key : keySet)
      {
         Color value = favoriteColours.get(key);
         System.out.println(key + " : " + value);
      }
   }
}