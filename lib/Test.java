package lib;
public class Test {
 /** returns the minimum of two numbers */
    public static int minFunction(int n1, int n2) {
       int min;
       if (n1 > n2)
          min = n2;
       else
          min = n1;

       return min;
    }

    public static int show(int m){
         return m*5;
    }

    public static int showNoParam(){
             return 5*5;
        }
}
