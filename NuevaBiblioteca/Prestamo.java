package NuevaBiblioteca;

public class Prestamo {
    private Libro libro;
    private Usuario usuario;

    public Prestamo(Libro libro, Usuario usuario ) {
        this.libro = libro;
        this.usuario = usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }


}
