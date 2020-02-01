public class BeadTracker {
  public static void main(String[] args) {
    int min = Integer.parseInt(args[0]);
    double tau = Double.parseDouble(args[1]);
    double delta = Double.parseDouble(args[2]);
    String[] imageNames = new String[args.length - 3]; 
    for (int i = 0; i < imageNames.length; i++) { 
      imageNames[i] = args[i + 3];
    }
    
    Picture input1 = null;
    Picture input2 = null;
    BeadFinder one = null;
    BeadFinder two = null;
    Blob[] blobs1 = null;
    Blob[] blobs2 = null;
    for (int i = 0; i < imageNames.length - 1; i++) {
      input1 = new Picture(imageNames[i]);
      input2 = new Picture(imageNames[i + 1]);
      one = new BeadFinder(input1, tau);
      two = new BeadFinder(input2, tau);
      blobs1 = one.getBeads(min);
      blobs2 = two.getBeads(min);
      
      for (int j = 0; j < blobs1.length; j++) {
        double minDistance = 9999999.9;
        for (int k = 0; k < blobs2.length; k++) {
          if (blobs1[j].distanceTo(blobs2[k]) < minDistance) {
            minDistance = blobs1[j].distanceTo(blobs2[k]);
          }
        }
        if (minDistance < delta) {
          System.out.println(minDistance);
        }
      }
      
    }
    
  }
}