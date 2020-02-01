public class TextGenerator { 
  public static void main(String[] args) {
    String fileName = args[0];
    int k = Integer.parseInt(args[1]);
    int T = Integer.parseInt(args[2]);
    
    In input = new In(fileName);
    String textInput = input.readAll();
    
    MarkovModel model = new MarkovModel(textInput, k);
    
    String startPoint = textInput.substring(0, k);
    String printed = startPoint;
    String incremented = startPoint;
    char random = 'a';
    int count = 1;
    
    while (printed.length() <= T) {
      random = model.random(incremented);
      printed += random;
      incremented = incremented.substring(count, incremented.length()) + random;
      count++;
    }
    
    System.out.println(printed);
  }
  
}