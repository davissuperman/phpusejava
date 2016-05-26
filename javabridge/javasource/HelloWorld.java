import javax.swing.JOptionPane;

public class HelloWorld {
  public static final String JAVABRIDGE_PORT="8080";


  public static void main(String args[]) throws Exception {
    System.out.println("Hello ");
    System.exit(0);
  }
 public void hello(String args[]) throws Exception {
    System.out.println( args[0]);
  }
}