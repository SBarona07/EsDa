package NuevaBiblioteca;

import java.util.ArrayList;

public class Libreria {
    private ArrayList<Libro> libros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Prestamo> prestamos;

    public Libreria() {
        this.libros = new ArrayList<Libro>();
        this.usuarios = new ArrayList<Usuario>();
        this.prestamos = new ArrayList<Prestamo>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
        System.out.println("Libro agregado: " + libro.getTitulo());
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuario agregado: " + usuario.getNombre());
    }

    public void registrarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
        System.out.println(
                "Préstamo registrado: " + prestamo.getLibro().getTitulo() + " a " + prestamo.getUsuario().getNombre());
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }

    public ArrayList<Libro> listarLibros() {
        return new ArrayList<Libro>(libros);
    }

    public Libro buscarPorId(String id) {
        if (id == null)
            return null;
        for (Libro l : libros) {
            if (id.equals(l.getId()))
                return l;
        }
        return null;
    }

    public ArrayList<Libro> buscarPorTitulo(String titulo) {
        ArrayList<Libro> resultado = new ArrayList<Libro>();
        if (titulo == null)
            return resultado;
        String t = titulo.toLowerCase();
        for (Libro l : libros) {
            if (l.getTitulo() != null && l.getTitulo().toLowerCase().contains(t)) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    // Nuevo: modificar campos de un libro por id (no reemplaza el objeto)
    public boolean modificarPorId(String id, String nuevoTitulo, String nuevoAutor) {
        Libro l = buscarPorId(id);
        if (l == null)
            return false;
        if (nuevoTitulo != null)
            l.setTitulo(nuevoTitulo);
        if (nuevoAutor != null)
            l.setAutor(nuevoAutor);
        return true;
    }

    // Nuevo: eliminar por id
    public boolean eliminarPorId(String id) {
        if (id == null)
            return false;
        return libros.removeIf(l -> id.equals(l.getId()));
    }

    public boolean modificarUsuarioPorId(String id, String nuevoNombre) {
        Usuario u = buscarUsuarioPorId(id);
        if (u == null)
            return false;
        if (nuevoNombre != null)
            u.setNombre(nuevoNombre);
        return true;
    }

    // Nuevo: buscar usuario por id
    public Usuario buscarUsuarioPorId(String id) {
        if (id == null)
            return null;
        for (Usuario u : usuarios) {
            if (id.equals(u.getId()))
                return u;
        }
        return null;
    }
}
