

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * This class contains methods to connect the front end interface with the data stored in the hash
 * table.
 * 
 * @author Sanjana Tarigoppula
 */
public class Backend {

  static HashTableMap<String, Integer> table = Wrangler.getHT(); // Access Hashtable from
                                                                 // Wrangler.Java


  /**
   * This method returns the amount of specified candy by accessing the value of the key
   * 
   * @param candy is the type of candy the customer wants to check stock on
   * @return amount of the candy that is available
   */
  public int stockAvail(String candy) {
    return table.get(candy);
  }


  /**
   * This method sets the stock of specified candy - only employees can do this (password is
   * "CANDY1234")
   * 
   * @param candy    is the type of candy the employee wants to update stock on
   * @param amount   of the candy that is being added
   * @param password is the password used to get into this feature
   * @return true if the function was successful, false if not 
   */
  public boolean setStock(String password, String candy, int amount) {
    if (password(password)) { // first employee has to get in using password 
      if (table.containsKey(candy)) { // now check if the candy exists in our database
        table.remove(candy); // if it does then remove it and then re-add with correct amount
        boolean put = table.put(candy, amount); 
        if (put == true) {
          return true;
        } else {
          return false;
        }
      } else { // if the candy doesn't exist 
        return false;
      }
    } else { // if password is incorrect
      return false;
    }
  }

  
  /**
   * This method prints out the inventory in the shop
   * 
   */
  public void printAll() {
    Scanner sc = null;;
    try {
      File file = new File("List.txt");
      sc = new Scanner(file);
      while (sc.hasNextLine()) { // Basically reading through the file and printing everything out
        System.out.println(sc.nextLine()); 
      }
    } catch (FileNotFoundException e) {
      System.out.print("file not found.");
    }
  }


  /**
   * This method makes sure that the password the employee inputs is correct.
   * 
   * @param password is the password used to get into this feature
   * @return true if the password is correct, false if not
   */
  public boolean password(String password) {
    if (password.equals("CANDY1234")) { // if the password is correct
      return true;
    } else {
      return false;
    }
  }


  /**
   * This method allows customers to purchase the candy
   * 
   * @param candy is the type of candy the customer wants to buy
   * @param amount of the candy the customer wants to buy
   */
  public boolean purchaseCandy(String candy, int amount) {
    if (table.containsKey(candy) && table.get(candy)!=0) { // check if the candy is available
      table.remove(candy); // if it is available then just remove it from the HT and add again
      boolean put = table.put(candy, amount); 
      if (put == true) { // if the updating was successful, then return true
        return true;
      } else {
        return false;
      }
    } else { // if the candy is not available 
      return false;
    }
  }

}
