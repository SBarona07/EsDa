package Clase2Matrices;

public class Clase {
    public static void main(String[] args) {
        double[][] notas = new double[3][4];
        
        // Estudiante 1
        notas[0][0] = 3.7;
        notas[0][1] = 4.2;
        notas[0][2] = 4.8;
        notas[0][3] = 4.2;

        // Estudiante 2
        notas[1][0] = 3.5;
        notas[1][1] = 4.0;
        notas[1][2] = 4.5;
        notas[1][3] = 3.8;

        // Estudiante 3
        notas[2][0] = 5.0;
        notas[2][1] = 4.8;
        notas[2][2] = 4.0;
        notas[2][3] = 2.2;

        // Imprimir las notas
        for (int i = 0; i < notas.length; i++) {
            System.out.println("Notas del estudiante " + (i + 1) + ": ");
            double suma = 0;
            for (int j = 0; j < notas[i].length; j++) {
                System.out.println(notas[i][j] + " ");
                suma += notas[i][j];
            }
            System.out.println("Promedio del estudiante " + (i + 1) + ": " + (suma / notas[i].length));
        }
        
    }
}