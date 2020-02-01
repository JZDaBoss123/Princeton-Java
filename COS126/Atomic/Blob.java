import java.util.HashMap; 
import java.util.Map; 
public class Blob {
  private int pixelCount;
  public HashMap<Integer, Integer> coordinates;
  private double sumX;
  private double sumY;
  
  public        Blob() {                    //  creates an empty blob
    pixelCount = 0;
    coordinates = new HashMap<>();
    sumX = 0.0;
    sumY = 0.0;
  }
  public   void add(int x, int y) {          //  adds pixel (x, y) to this blob
    coordinates.put(x, y);
    pixelCount++;
    sumX += x;
    sumY += y;
  }
  public    int mass()                    { //  number of pixels added to this blob
    return pixelCount;
  }
  public double distanceTo(Blob that) {     //  Euclidean distance between the center of masses of the two blobs
    double x2 = Math.pow(that.centerX() - this.centerX() , 2);
    double y2 = Math.pow(that.centerY() - this.centerY(), 2);
    
    return Math.sqrt(x2 + y2);
  }
  public String toString() {                //  string representation of this blob (see below)
    System.out.print(pixelCount + " ");
    System.out.println("(" + this.centerX() +", " + this.centerY() + ")");
    return "";
  }
  private double centerX() { //center of mass X coordinates

    return sumX / pixelCount;
  }
  private double centerY() {//center of mass Y coordinate
    double sum = 0.0;
    for (int x : coordinates.keySet()) {
      sum += coordinates.get(x);
    }
    return sumY / pixelCount;
  }
  public static void main(String[] args) {  //  tests this class by directly calling all instance methods
    Blob b = new Blob();
    b.add(10, 100);
    b.add(1,10);
    b.add(3,11);
    b.add(13,17);
    System.out.println(b);
    Blob x = new Blob();
    Blob y = new Blob();
    x.add(0,0);
    y.add(1,1);
    System.out.println(x.distanceTo(y));
  }
}