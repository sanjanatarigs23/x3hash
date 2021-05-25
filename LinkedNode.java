
/**
 * This class models each node of the linked list within the hash table array indexes. 
 * 
 * @author Sanjana Tarigoppula
 */


public class LinkedNode <KeyType, ValueType> {
    
  private KeyType key; // a unique address that will be hashed to the corresponding value
  private ValueType value; // what the key points to within the hash table
  
  
  /**
   * Constructs a node within the linked list holding the key value pair 
   * 
   * @author Sanjana Tarigoppula
   */
  public LinkedNode(KeyType key, ValueType value) {
    this.value = value;
    this.key = key;
  }


  
  /**
   * Returns the key of the key value pair
   * 
   * @return key of the key value pair
   * 
   */
  public KeyType getKey() {
    return key;
  }



  /**
   * Grabs the value of the key value pair - basically what the key is pointing to
   * 
   * @return the value of the key value pair
   * 
   */
  public ValueType getValue() {
    return value;
  }
  
  
  
  
}
