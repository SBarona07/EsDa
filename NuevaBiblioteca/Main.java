package NuevaBiblioteca;

public class Main {
    public static void main(String[] args) {
        Libreria libreria = new Libreria();

        // Crear libros
        Libro l1 = new Libro("1", "Legends of Zelda", "Nintendo");
        Libro l2 = new Libro("2", "Mario Bros", "Nintendo");
        Libro l3 = new Libro("3", "Zelda: Breath of the Wild", "Nintendo");
        libreria.agregarLibro(l1);
        libreria.agregarLibro(l2);
        libreria.agregarLibro(l3);

        // Crear usuarios
        Usuario u1 = new Usuario("u1", "Zelda Princesa");
        Usuario u2 = new Usuario("u2", "Mario");
        Usuario u3 = new Usuario("u3", "Link");
        libreria.agregarUsuario(u1);
        libreria.agregarUsuario(u2);
        libreria.agregarUsuario(u3);


        // Registrar préstamo
        Prestamo p1 = new Prestamo(l2, u3);
        libreria.registrarPrestamo(p1);

        // Listar usuarios
        System.out.println("\nListado de usuarios:");
        for (Usuario u : libreria.getUsuarios()) {
            System.out.println("- " + u.getId() + ": " + u.getNombre());
        }

        // Listar libros
        System.out.println("\nListado de libros:");
        for (Libro l : libreria.listarLibros()) {
            System.out.println("- " + l.getId() + ": " + l.getTitulo() + " — " + l.getAutor());
        }
        
        //Modificar Usuario
        System.out.println("\nModificar usuario id=u1:");
        boolean modUsuario = libreria.modificarUsuarioPorId("u1", "Ash Ketchum");
        System.out.println("Modificado: " + modUsuario);
        u1 = libreria.buscarUsuarioPorId("u1");
        System.out.println("Nombre actual: " + (u1 != null ? u1.getNombre() : "no encontrado"));

        // Buscar por título
        System.out.println("\nBuscar por título 'Zelda':");
        for (Libro l : libreria.buscarPorTitulo("Zelda")) {
            System.out.println("- " + l.getId() + ": " + l.getTitulo());
        }

        // Modificar libro
        System.out.println("\nModificar libro id=1:");
        boolean modificado = libreria.modificarPorId("1", "Spiderman", "Insomniac Games");
        System.out.println("Modificado: " + modificado);
        Libro lib1 = libreria.buscarPorId("1");
        System.out.println("Título actual: " + (lib1 != null ? lib1.getTitulo() : "no encontrado"));

        // Eliminar libro
        System.out.println("\nEliminar libro id=3:");
        boolean eliminado = libreria.eliminarPorId("3");
        System.out.println("Eliminado: " + eliminado);

        // Estado final
        System.out.println("\nLibros finales:");
        for (Libro l : libreria.getLibros()) {
            System.out.println("- " + l.getId() + ": " + l.getTitulo());
        }
        
        System.out.println("\nUsuarios finales:");
        for (Usuario u : libreria.getUsuarios()) {
            System.out.println("- " + u.getId() + ": " + u.getNombre());
        }
    }
}