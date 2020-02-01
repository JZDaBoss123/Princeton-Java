import java.util.HashMap; 
import java.util.Map; 

public class MarkovModel {
  private int order;
  private String input;
  private HashMap<String, Integer> kMap;
  private HashMap<String, String> charMap;
  
  // creates a Markov model of order k for the specified text
  public MarkovModel(String text, int k) {
    this.order = k;
    this.input = text;
    kMap = new HashMap<>();
    charMap = new HashMap<>();
    
    int temp = 0;
    int holder = 0;
    String circularString = "";
    String charHolder = "";
    //substring from i to i+k, if identical....
    for (int i = 0; i < text.length(); i++) {
      if (i + k >= text.length()) {
        holder = i + k - text.length();
        circularString = text.substring(i, text.length()) +
          text.substring(0, holder);
        if (kMap.containsKey(circularString)) {
          temp = kMap.get(circularString);
          kMap.put(circularString, temp + 1);
          //
          charHolder = charMap.get(circularString);
          if (charHolder == null) {
            charMap.put(circularString, "" + text.charAt(holder));
          } else {
            charMap.put(circularString , charHolder + text.charAt(holder));
          }
          
        } else {
          kMap.put(circularString, 1);
          charHolder = charMap.get(circularString);
          if (charHolder == null) {
            charMap.put(circularString, "" + text.charAt(holder));
          } else {
            charMap.put(circularString , charHolder + text.charAt(holder));
          }
          //substr of i to end, 0 to holder.
        }
      }
      else if (kMap.containsKey(text.substring(i, i+k))) {
        temp = kMap.get(text.substring(i, i+k));
        kMap.put(text.substring(i, i+k), temp + 1);
        
        charHolder = charMap.get(text.substring(i, i+k));
        if (charHolder == null) {
          if (i + k == text.length() - 1) {
            charMap.put(text.substring(i, i+k), "" + text.charAt(0));
          } else {
            charMap.put(text.substring(i, i+k), "" + text.charAt(i + k));
          }
        } else {
          if (i + k == text.length() - 1) {
            charMap.put(text.substring(i, i+k), charHolder + text.charAt(0));
          } else {
            charMap.put(text.substring(i, i+k) , charHolder + text.charAt(i + k));
          }
        }
        
      } else {
        kMap.put(text.substring(i, i+k), 1); 
        charHolder = charMap.get(text.substring(i, i+k));
        if (charHolder == null) {
          if (i + k == text.length() - 1) {
            charMap.put(text.substring(i, i+k), "" + text.charAt(text.length() - 1));
          } else {
            charMap.put(text.substring(i, i+k), "" + text.charAt(i + k));
          }
        } else {
          if (i + k == text.length() - 1) {
            charMap.put(text.substring(i, i+k), charHolder + text.charAt(text.length() - 1));
          } else {
            charMap.put(text.substring(i, i+k) , charHolder + text.charAt(i + k));
          }
        }
      }
    }
    
  }
  
  // returns the order k of this Markov model
  public int order() {
    return order;
  }
  
  // returns a string representation of the Markov model (as described below)
  public String toString() {
    for (String key: charMap.keySet()) {
      System.out.println("key: " + key + "\0" + " map: " + charMap.get(key));
    }
    return "";
  }
  
  // returns the number of times the specified kgram appears in the text
  public int freq(String kgram) {
    if (kgram.length() != order) {
      throw new IllegalArgumentException ("kgram is of the wrong length");
    }
    
    return kMap.get(kgram);
    
  }
  
  // returns the number of times the character c follows the specified
  // kgram in the text
  public int freq(String kgram, char c) {
    if (kgram.length() != order) {
      throw new IllegalArgumentException ("kgram is of the wrong length");
    }
    int count = 0;
    String test = charMap.get(kgram);
    for (int i = 0; i < test.length(); i++) {
      if (test.charAt(i) == c) {
        count++;
      }
    }
    return count;
    
  }
  
  // returns a random character that follows the specified kgram in the text,
  // chosen with weight proportional to the number of times that character
  // follows the specified kgram in the text
  public char random(String kgram) {
    if (kgram.length() != order) {
      throw new IllegalArgumentException ("kgram is of the wrong length");
    }
    String get = charMap.get(kgram);
    int getLength = get.length();
    int index = (int) (Math.random() * getLength);
    return get.charAt(index);
  }
  
  // tests this class by directly calling all instance methods
  public static void main(String[] args) {
//    MarkovModel z = new MarkovModel("abcde", 2);
//    System.out.println(z);
    MarkovModel m = new MarkovModel("gagggagaggcgagaaa", 2);
    System.out.println(m.random("cg"));
    System.out.println(m.toString());
    System.out.println(m.freq("aa"));
    System.out.println(m.freq("aa", 'c'));
  }
}