package Clase12;

public class Contador {
    public SalidaContador contar(String Cadena){
        SalidaContador salida = new SalidaContador();
        
        salida.setCantidadPalabras(contarPalabrasFrase(Cadena));
        salida.setCantidadCaracteres(contarCaracteres(Cadena));
        salida.setCantidadVocales(contarVocales(Cadena));
        salida.setLongitudPalabraMasLarga(identificarPalabraMasLarga(Cadena));
        
        return salida;


    }

    private int contarPalabrasFrase(String Cadena){
        String[] palabras = Cadena.split(" ");
        return palabras.length;
    }

    private int contarCaracteres(String Cadena){
        return Cadena.length();
    }

    private int contarVocales(String Cadena){
        int contadorVocales = 0;
        String vocales = "aeiouAEIOU";
        for (int i = 0; i < Cadena.length(); i++) {
            char c = Cadena.charAt(i);
            if (vocales.indexOf(c) != -1) {
                contadorVocales++;
            }
        }
        return contadorVocales;
    }


    private int identificarPalabraMasLarga(String Cadena){
        String[] palabras = Cadena.split(" ");
        int maxLength = 0;
        for (String palabra : palabras) {
            if (palabra.length() > maxLength) {
                maxLength = palabra.length();
            }
        }
        return maxLength;
    }
}


