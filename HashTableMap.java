import java.util.NoSuchElementException;
import java.util.LinkedList;


/**
 * This class models a Hash Table Map which creates a Hash Table using key and value pairs.
 */

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  private int capacity; // the total number of spots available within the hash table array
  private int size; // the number of spots filled in the table with hash table pairs
  private LinkedList<LinkedNode<KeyType, ValueType>>[] table; // array of linked lists storing key
                                                              // value pairs


  /**
   * Constructs a Hash Table Map which is the array of linked lists, and it stores the default
   * capacity and size of the table.
   * 
   */
  public HashTableMap() {
    capacity = 10;
    size = 0;
    table = new LinkedList[capacity];

  }



  /**
   * Constructs a Hash Table Map which is the array of linked lists, and it stores the capacity
   * specified by the user and size of the table.
   * 
   */
  public HashTableMap(int capacity) {
    this.capacity = capacity;
    size = 0;
    table = new LinkedList[capacity];
  }



  /**
   * Inserts the key and value pair into the array of linked lists if the key doesn't already exist
   * 
   * @param key   a unique address that will be hashed to the corresponding value
   * @param value is what the key points to within the hash table
   * @return true if the key value pair was successfully added the table, false otherwise
   * 
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    if (containsKey(key) == true) { // if the key already exists, do not add it again to prevent
                                    // collision
      return false;
    }

    int index = (Math.abs(key.hashCode())) % capacity; // index where the key value pair is stored
                                                       // within the array
    LinkedList<LinkedNode<KeyType, ValueType>> list = table[index]; // extracting the linked list of
                                                                    // the index where the key value
                                                                    // pair is stored
    LinkedNode pair = new LinkedNode(key, value); // new node within the linked list which will
                                                  // store the key value pair

    if (list == null) { // first check if the linked list exists
      list = new LinkedList(); // if it doesn't, create a new linked list and add the new node
      list.add(new LinkedNode(key, value));
      table[index] = list; // set the index of the original table to the newly created linked list
      size++; // increment size to make up for the added pair
      return true;
    }

    list.add(pair); // if the linked list exists already, simply add the pair to the end of this
                    // list
    table[index] = list;
    size++;

    double loadCapacity = (double) size / (double) capacity; // in case of rehashing, calculate how
                                                             // much the array has been filled up
    
    if (loadCapacity >= 0.8) { // if 80% of the array is occupied, rehash
      rehash();
    }

    return true;


  }



  /**
   * Rearranges the hash table when its capacity doubles
   * 
   */
  private void rehash() {
    capacity = capacity * 2;
    // creating a new array of linked lists representing the new hash table
    LinkedList<LinkedNode<KeyType, ValueType>>[] newTable = new LinkedList[capacity];

    for (int i = 0; i < table.length; i++) {
      if (table[i] == null) // if nothing exists in the given index, continue with rehashing
        continue;
      for (int j = 0; j < table[i].size(); j++) {
        LinkedNode<KeyType, ValueType> pair = table[i].get(j); // getting the key value pair
        KeyType pairKey = pair.getKey(); // getting the key of that pair
        int newIndex = Math.abs(pairKey.hashCode()) % capacity; // calculating new index of key

        if (newTable[newIndex] == null) { // if the new index doesn't have anything, add a linked
                                          // list
          newTable[newIndex] = new LinkedList<>();
          newTable[newIndex].add(pair); // add the key value pair to the linked list
        } else {
          newTable[newIndex].add(pair); // if not empty, just add the pair to the end
        }

      }
    }

    table = newTable; // assign the old table to the new table to finish rehashing

  }



  /**
   * Grabs and returns the value to which the specified key is mapped 
   * 
   * @param key whose associated value is to be returned 
   * @return the value to which the specified key is mapped 
   * 
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    int index = (Math.abs(key.hashCode())) % capacity; // first calculate the index using hash 
    LinkedList<LinkedNode<KeyType, ValueType>> list = table[index]; // get the linked list of index

    if (list == null) { // if the linked list doesn't have anything, that means there's no value
      throw new NoSuchElementException("No values exist for this key (null).");
    }

    for (int i = 0; i < list.size(); i++) { // go through the linked list if it's not null
      if (list.get(i).getKey().equals(key)) { // if there is a matching key, return its value
        return list.get(i).getValue();
      }
    }

    throw new NoSuchElementException("No values exist for this key.");
  }



  /**
   * Returns the size of the hash table 
   * 
   * @return the size of the hash table
   * 
   */
  @Override
  public int size() {
    return size;
  }
  
  
  
  /**
   * Returns the capacity of the hash table (for testing purposes)
   * 
   * @return the capacity of the hash table
   * 
   */
  public int capacity() {
    return capacity;
  }



  /**
   * Checks if the map contains a mapping for the specified key
   * 
   * @return true if the map contains a mapping for the key
   */
  @Override
  public boolean containsKey(KeyType key) {

    try {
      get((KeyType) key); // try to get the value of the key 
    } catch (NoSuchElementException e) {
      return false; // returns false if the value does not exist 
    }

    return true;
  }



  /**
   * Removes the mapping for a key from this map if it's present
   * 
   * @param key whose associated value is to be removed
   * @return the value to which the specified key is mapped, or null if there's no mapping
   * 
   */
  @Override
  public ValueType remove(KeyType key) {
    int index = (Math.abs(key.hashCode())) % capacity; // first find index of where pair is located
    LinkedList<LinkedNode<KeyType, ValueType>> list = table[index];

    if (list == null) { 
      return null;
    }

    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getKey().equals(key)) { // if a key is found within the linked list, get value
        ValueType value = list.get(i).getValue();
        list.remove(i); // remove using linked list class method
        size--; // decrement size to account for the removal
        return value;
      }
    }

    return null; // if list is not null but the key isn't found
  }



  /**
   * Clears the hash table and all key value pairs in it
   * 
   */
  @Override
  public void clear() {
    table = new LinkedList[capacity]; // assigning the table to a brand new linked list array 
    size = 0; // size is 0 because there are no pairs 
  }

  
  
  
  
  
  


}
