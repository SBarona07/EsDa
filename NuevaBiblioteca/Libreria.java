package NuevaBiblioteca;

import java.util.ArrayList;
import java.util.Stack;

public class Libreria {
    private ArrayList<Libro> libros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Prestamo> prestamos;
    private Stack<Transaccion> historial;

    public Libreria() {
        this.libros = new ArrayList<Libro>();
        this.usuarios = new ArrayList<Usuario>();
        this.prestamos = new ArrayList<Prestamo>(); //
        this.historial = new Stack<Transaccion>(); 
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
        historial.push(new Transaccion("libro", libro));
        System.out.println("- Libro agregado: " + libro.getTitulo());

    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        historial.push(new Transaccion("usuario", usuario));
        System.out.println("- Usuario agregado: " + usuario.getNombre());
    }

    public void registrarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
        historial.push(new Transaccion("prestamo", prestamo));
        System.out.println(
                "- Préstamo registrado: " + prestamo.getLibro().getTitulo() + " a "
                        + prestamo.getUsuario().getNombre());
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

    public boolean modificarLibroPorId(String id, String nuevoTitulo, String nuevoAutor) {
        Libro l = buscarPorId(id);
        if (l == null)
            return false;
        Libro estadoAnterior = new Libro(l.getId(), l.getTitulo(), l.getAutor());
        historial.push(new Transaccion("Modificar libro", estadoAnterior));
        if (nuevoTitulo != null)
            l.setTitulo(nuevoTitulo);
        if (nuevoAutor != null)
            l.setAutor(nuevoAutor);
        return true;
    }

    public boolean eliminarLibroPorId(String id) {
        if (id == null)
            return false;
        historial.push(new Transaccion("eliminar_libro", buscarPorId(id)));
        return libros.removeIf(l -> id.equals(l.getId()));
    }

    public boolean modificarUsuarioPorId(String id, String nuevoNombre) {
        Usuario u = buscarUsuarioPorId(id);
        if (u == null)
            return false;
        Usuario estadoAnterior = new Usuario(u.getId(), u.getNombre());
        historial.push(new Transaccion("Modificar usuario", estadoAnterior));
        if (nuevoNombre != null)
            u.setNombre(nuevoNombre);
        return true;
    }

    public Usuario buscarUsuarioPorId(String id) {
        if (id == null)
            return null;
        for (Usuario u : usuarios) {
            if (id.equals(u.getId()))
                return u;
        }
        return null;
    }

    public boolean eliminarUsuarioPorId(String id) {
        if (id == null)
            return false;
        historial.push(new Transaccion("eliminar_usuario", buscarUsuarioPorId(id)));
        return usuarios.removeIf(u -> id.equals(u.getId()));
    }

    public void deshacer() {
        if (historial.isEmpty()) {
            System.out.println("- No hay transacciones para deshacer");
            return;
        }

        Transaccion t = historial.pop();
        switch (t.getTipo()) {
            case "libro":
                Libro libro = (Libro) t.getObjeto();
                libros.remove(libro);
                System.out.println("- Deshacer: Libro eliminado: " + libro.getTitulo());
                break;
            case "usuario":
                Usuario usuario = (Usuario) t.getObjeto();
                usuarios.remove(usuario);
                System.out.println("- Deshacer: Usuario eliminado: " + usuario.getNombre());
                break;
            case "prestamo":
                Prestamo prestamo = (Prestamo) t.getObjeto();
                prestamos.remove(prestamo);
                System.out.println("- Deshacer: Préstamo eliminado: " + prestamo.getLibro().getTitulo() + " a "
                        + prestamo.getUsuario().getNombre());
                break;
            case "eliminar_libro":
                Libro libroEliminado = (Libro) t.getObjeto();
                libros.add(libroEliminado);
                System.out.println("- Deshacer: Libro restaurado: " + libroEliminado.getTitulo());
                break;
            case "eliminar_usuario":
                Usuario usuarioEliminado = (Usuario) t.getObjeto();
                usuarios.add(usuarioEliminado);
                System.out.println("- Deshacer: Usuario restaurado: " + usuarioEliminado.getNombre());
                break;
            case "Modificar libro":
                Libro estadoAnteriorLibro = (Libro) t.getObjeto();
                Libro libroActual = buscarPorId(estadoAnteriorLibro.getId());
                if (libroActual != null) {
                    libroActual.setTitulo(estadoAnteriorLibro.getTitulo());
                    libroActual.setAutor(estadoAnteriorLibro.getAutor());
                    System.out.println("- Deshacer: Modificación de libro revertida: " + libroActual.getTitulo());
                }
                break;
            case "Modificar usuario":
                Usuario estadoAnteriorUsuario = (Usuario) t.getObjeto();
                Usuario usuarioActual = buscarUsuarioPorId(estadoAnteriorUsuario.getId());
                if (usuarioActual != null) {
                    usuarioActual.setNombre(estadoAnteriorUsuario.getNombre());
                    System.out.println("- Deshacer: Modificación de usuario revertida: " + usuarioActual.getNombre());
                }
                break;

            default:
                break;
        }
    }
}
