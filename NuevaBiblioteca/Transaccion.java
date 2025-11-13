package NuevaBiblioteca;

public class Transaccion {
    private String tipo;
    private Object objeto;
    

    public Transaccion(String tipo, Object objeto) {
        this.tipo = tipo;
        this.objeto = objeto;
    }

    public String getTipo() {
        return tipo;
    }

    public Object getObjeto() {
        return objeto;
    }
}