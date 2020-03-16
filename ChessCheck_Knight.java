//  Alexander Kyprianou
//  COP 3503

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Hashtable;
import static java.lang.Math.pow;
import java.util.Iterator;

public class SneakyKnights {

  public static boolean
  allTheKnightsAreSafe(ArrayList<String> coordinateStrings, int boardSize)
  {
    int numCoords = coordinateStrings.size();
    String sub1, sub2;

    HashMap<Integer, ArrayList<Integer>> convertedCoords = new HashMap<>();

//Iterate through the alphanumeric coordinateStrings and convert them into
//numeric coordinates. Save the column number by replacing all numbers in
//a given coordinate with white space and calling my convertCoord method.
//Save the row number by replacing all letters in a given coordinate to white space.
    for(String i : coordinateStrings)
    {

      sub1 = i.replaceAll("[^a-z]", "");
      sub2 = i.replaceAll("[a-z]", "");

//Using my putCoord method, add the coordinates to my HashMap
      putCoord(convertCoord(sub1), Integer.parseInt(sub2), convertedCoords);
    }

//Now that the convertCoord HashMap is populated, we can check to see if any
//of the knights are in danger
      Iterator<Integer> i = convertedCoords.keySet().iterator();
      int currX, currY;
      ArrayList<Integer> tempY = null;
      ArrayList<Integer> temp2 = null;

      while(i.hasNext())
      {
        currX = i.next();
        tempY = convertedCoords.get(currX);

        temp2 = convertedCoords.get(currX + 2);
        if(temp2 != null)
          for(int j = 0; j < tempY.size(); j++)
          {
            currY = tempY.get(j);
            if(temp2.contains(currY + 1) || temp2.contains(currY - 1))
              return false;
          }

        temp2 = convertedCoords.get(currX - 2);
        if(temp2 != null)
          for(int j = 0; j < tempY.size(); j++)
          {
            currY = tempY.get(j);
            if(temp2.contains(currY + 1) || temp2.contains(currY - 1))
              return false;
          }

        temp2 = convertedCoords.get(currX + 1);
        if(temp2 != null)
          for(int j = 0; j < tempY.size(); j++)
          {
            currY = tempY.get(j);
            if(temp2.contains(currY + 2) || temp2.contains(currY - 2))
              return false;
          }

        temp2 = convertedCoords.get(currX - 1);
        if(temp2 != null)
         for(int j = 0; j < tempY.size(); j++)
          {
            currY = tempY.get(j);
            if(temp2.contains(currY + 2) || temp2.contains(currY - 2))
              return false;
          }
      }
      return true;
  }

//Convert alphabetic coordinates to numeric coordinates using base conversion
  public static int convertCoord(String sub)
  {
    double k = 0.0;
    int val = 0;

    char[] arr = sub.toCharArray();

    for(int i = arr.length-1; i >= 0; i--)
    {
        val = val + (arr[i] - 96)*((int)pow(26, k));
        k++;
    }
    return val;
  }

//This method will allow us to store all 'y' coordinate values with their 'x'
//coordinate counterpart. In the hashmap, the 'key' will refer to the x values
//and the 'value' will refer to an ArrayList holding all corresponding y values.
  public static void putCoord
  (Integer x, Integer y, HashMap<Integer, ArrayList<Integer>> convertedCoords)
  {
    ArrayList<Integer> temp = convertedCoords.get(x);

    if( temp == null)
      temp = new ArrayList<Integer>();

    temp.add(y);
    convertedCoords.put(x, temp);
    return;
  }


  public static double hoursSpent(){
    return 4.5;
  }

  public static double difficultyRating(){
    return 2.7;
  }


}
