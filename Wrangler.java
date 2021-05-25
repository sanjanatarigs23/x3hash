
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Wrangler {
  public static HashTableMap<String, Integer> table = new HashTableMap<String, Integer>();

  /**
   * this method read csv file and put them into hashtable as key (chocolate brands)and
   * value(storage amount).
   */
  public static void data() {

    try {
      BufferedReader br = new BufferedReader(new FileReader("Inventory.csv"));
      String line = null;

      while ((line = br.readLine()) != null) {
        String str[] = line.split(",");
        for (int i = 0; i < str.length; i++) {
          String arr[] = str[i].split(":");
          int stock = Integer.parseInt(arr[1]);
          table.put(arr[0], stock);
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("Warning:file not found!!!");
      e.printStackTrace();
    } catch (NumberFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  public static HashTableMap getHT() {
    data();
    return table;
  }
}