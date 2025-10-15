package Clase9;

public class Recursividad1 {
    public static void main(String[] args) {
        cuentaRegresiva(0);

    }

    public static void cuentaRegresiva(int n) {
        if (n == 10) {
            System.out.println("¡Despegue!");
            return;
        } 
             System.out.println(n);
            cuentaRegresiva(n+1);
        }

        public static int factorial(int n) {
            if (n == 0){ 
            return 1;
        }
        return n * factorial(n - 1);
    k }
        
    }


