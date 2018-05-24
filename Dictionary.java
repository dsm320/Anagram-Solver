/*
CSE 17
David Mancuso
dsm320
Program #4;     DEADLINE: November 19, 2016
Program Description: Word Jumble Solver
*/

import java.util.*;

/**A class to store all words in a particular dictionary */
public class Dictionary{
  
  //fields
  private int maxWordLength;
  private ArrayList<String>[] dictionary;
  private int count;
  
  /**A constructor that creates an instance of Dictionary with a given maximum word length*/
  public Dictionary(int maxWordLength){
    this.maxWordLength = maxWordLength;
    //gives warning but because of type erasure Heflin says its okay
    dictionary = (ArrayList<String>[]) new ArrayList[this.maxWordLength];
    for(int i=0; i<maxWordLength; i++)
      dictionary[i] = new ArrayList<String>();
  }
  
  /**A constructor that creates an instance of Dictionary with a maximum word length of 10*/
  public Dictionary(){
    this(10);
  }
  
  /**A method that returns the field maxWordLength*/
  public int getMaxWordLength(){
    return maxWordLength;
  }
  
  /**A method that returs that field count*/
  public int getCount(){
    return count;
  }
  
  /**A method that adds a given word to a Dictionary object provided it is the proper length*/
  public void addEntry(String word){
    if(word.length() <= maxWordLength){
      try{
        dictionary[word.length()-1].add(word.toLowerCase());
      } catch(ArrayIndexOutOfBoundsException ex) {
        //do nothing
      }
      count++;
    }
  }
  
  /**A recursive helper method that finds if a given String is contained in a Dictionary object*/
  public boolean lookup(String s){
    try{
      return lookup(0, dictionary[s.length()-1].size()-1, s.toLowerCase());
    // recursive calls continue if object not found so causes StackOverflow therefore will return false
    } catch (StackOverflowError ex) {
        return false;
    } catch (ArrayIndexOutOfBoundsException ex) {
        return false;
    }
  }
  
  /**A method that performs a recursive binary search for a given String in a Dictionary object*/
  public boolean lookup(int start, int end, String s){
    int mid = (start+end)/2;
    int dicLen = s.length()-1;
  //testing search method b/c had problems
    //System.out.println(dictionary.length);
    //for(int i =0; i<dictionary.length; i++){
      //System.out.println(dictionary[i].size());
    //}
    //System.out.println(dictionary[dicLen].get(mid));
    if(start > end)
      return false;
    else if(end==start+1){
      if(dictionary[dicLen].get(end).equals(s))
        return true;
      else if(dictionary[dicLen].get(start).equals(s))
        return true;
      return false;
    }
    else if(dictionary[dicLen].get(mid).equals(s))
      return true;
    else if(dictionary[dicLen].get(mid).compareTo(s) < 0)
      return lookup(mid, end, s);
    else if(dictionary[dicLen].get(mid).compareTo(s) > 0)
      return lookup(start, mid, s);
    else
      return false;
  }
  
  /*
  //test main method
  public static void main(String[] args){
    Dictionary d = new Dictionary();
    d.addEntry("Paris");
    d.addEntry("London");
    d.addEntry("Tokyo");
    d.addEntry("New York");
    d.addEntry("Boston");
    d.addEntry("Washington");
    d.addEntry("aaaaaaaaaaaaaaa");
    
    System.out.print(d.lookup("aaaaaaaaaaaaaaa"));
  }
  */
  
}
