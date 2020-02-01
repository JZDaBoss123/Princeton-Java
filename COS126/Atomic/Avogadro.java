public class Avogadro {
  public static void main(String[] args) {
    String filename = args[0];
    In input = new In(filename);
    double[] displacements = input.readAllDoubles();
    
    //convert pixels to meters
    for (int i = 0; i < displacements.length; i++) {
      displacements[i] = displacements[i] * 0.175 * Math.pow(10, -6);
    }
    
    //self diffusion constant
    double D = 0;
    int count = 0;
    double sum = 0;
    for (int i = 0; i < displacements.length; i++) {
      sum += Math.pow(displacements[i], 2);
      count++;
    }
    D = sum / (count * 2);
    
    //Boltzmann constant
    double temperature = 297.0;
    double viscosity = 9.135 * Math.pow(10, -4);
    double radius = 0.5 * Math.pow(10, -6);
    
    double boltzmann = (D * 6 * Math.PI * viscosity * radius) / temperature;
    
    //Avogadro
    double gas = 8.31446;
    double avogadro = gas / boltzmann;
    
    System.out.println("Boltzmann = " + boltzmann);
    System.out.println("Avogadro = " + avogadro);
    
  }
  
  
}