/*
CSE 17
David Mancuso
dsm320
Program #4;     DEADLINE: November 19, 2016
Program Description: Word Jumble Solver
*/

import java.util.*;
import java.io.*;

/**A class that finds all permutations of a String*/
public class Jumble{
  
  //fields
  private static ArrayList<String> permutations;
  private static String[] perms;
  
  /**A helper method that finds permutations of a given String by calling the other findPermutations method*/
  public static String[] findPermutations(String s) {
    permutations = new ArrayList<String>();
    findPermutations(s, "", permutations);
    perms = new String[permutations.size()];
    for(int i=0; i<perms.length; i++)
      perms[i] = permutations.get(i);
    return perms;
  }
  
  /**A method that recursively finds the permutations of a given String*/
  private static void findPermutations(String s1, String s2, ArrayList<String> permutations) {
    int len = s1.length();
    if (len == 0 && !permutations.contains(s2))
      permutations.add(s2);
    else {
      for (int i = 0; i < len; i++)
        //might not be super effecient because creating a new String everytime... but it works
        findPermutations(s1.substring(0, i) + s1.substring(i+1, len), s2 + s1.charAt(i), permutations);
    }
  }
  
  /*
  //test main method
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String test = in.next();
    findPermutations(test);
    //used the ComputeFactorial class we wrote in class to limit for-loop
    for(int i=0; i<(int)ComputeFactorial.factorial(test.length()); i++){
      System.out.println(perms[i]);
    }
  }
  */
  
  
  //main method
  public static void main(String[] args){
    
    Dictionary d = null;
    
    File file = null;
    
    if(args.length==1){
      d = new Dictionary();
      file = new File(args[0]);
    }
    else if(args.length==2){
      int num = Integer.parseInt(args[0]);
      d = new Dictionary(num);
      file = new File(args[1]);
    }
    else{
      System.out.println("Usage: java Jumble wordLength fileName \nUsage: java Jumble fileName");
      System.exit(3);
    }
    
    Scanner input = null;
    try{
      input = new Scanner(file);
    } catch(FileNotFoundException ex){
      System.out.println("Error: Cannot read from source file");
      System.exit(1);
    }
    
    while(input.hasNextLine()){
      d.addEntry(input.next());
    }
    
    System.out.println("Read in " + d.getCount() + " words \n");
    do{
      System.out.print("Enter a scrambled word: ");
      input = new Scanner(System.in);
      String s = input.nextLine();
      if (s.equals("")){
        break;
      }
      //used to check word is shorter than the max word length of the dictionary
      if(s.length()>d.getMaxWordLength()){
        System.out.println("Please enter a word less than " + d.getMaxWordLength() + " characters.");
      }
      //finds permutations and compares to entries in dictionary
      else{
        findPermutations(s);
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0; i<perms.length; i++){
          if(d.lookup(perms[i])){
            list.add(i);
          }
        }
        try{
          if(list.size()>0){
            System.out.println("The words formed from '" + s + "' are:");
            for(int i=0; i<perms.length; i++)
              System.out.println(perms[list.get(i)]);
          } 
        } catch(IndexOutOfBoundsException ex){
          //do nothing
        }
        if(list.size()==0){
          System.out.println("No words are formed from '" + s + "'\n");
        }
      }
    } while(true);
    
    System.out.println("Goodbye!");
    
  }
  
}
