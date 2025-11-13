package NuevaBiblioteca;

public class Main {
    public static void main(String[] args) {
        Libreria libreria = new Libreria();

        // Crear libros
        System.out.println("Listado de libros agregados:");
        Libro l1 = new Libro("1", "Legends of Zelda", "Nintendo");
        Libro l2 = new Libro("2", "Mario Bros", "Nintendo");
        Libro l3 = new Libro("3", "Zelda: Breath of the Wild", "Nintendo");
        Libro l4 = new Libro("4", "Hogwarts Legacy", "J.K. Rowling");
        Libro l5 = new Libro("5", "The Witcher 3", "Andrzej Sapkowski");
        Libro l6 = new Libro("6", "Cyberpunk 2077", "CD Projekt");
        Libro l7 = new Libro("7", "Elden Ring", "FromSoftware");
        Libro l8 = new Libro("8", "God of War", "Santa Monica Studio");
        Libro l9 = new Libro("9", "Final Fantasy VII", "Square Enix");
        Libro l10 = new Libro("10", "The Last of Us", "Naughty Dog");
        libreria.agregarLibro(l1);
        libreria.agregarLibro(l2);
        libreria.agregarLibro(l3);
        libreria.agregarLibro(l4);
        libreria.agregarLibro(l5);
        libreria.agregarLibro(l6);
        libreria.agregarLibro(l7);
        libreria.agregarLibro(l8);
        libreria.agregarLibro(l9);
        libreria.agregarLibro(l10);
        libreria.deshacer();
        System.out.println("");

        // Crear usuarios
        System.out.println("Listado de usuarios agregados:");
        Usuario u1 = new Usuario("U1", "Zelda Princesa");
        Usuario u2 = new Usuario("U2", "Mario");
        Usuario u3 = new Usuario("U3", "Link");
        Usuario u4 = new Usuario("U4", "Bowser");
        Usuario u5 = new Usuario("U5", "Ganondorf");
        Usuario u6 = new Usuario("U6", "Toad");
        Usuario u7 = new Usuario("U7", "Atreus");
        Usuario u8 = new Usuario("U8", "Kratos");
        Usuario u9 = new Usuario("U9", "Geralt de Rivia");
        Usuario u10 = new Usuario("U10", "Cloud Strife");
        libreria.agregarUsuario(u1);
        libreria.agregarUsuario(u2);
        libreria.agregarUsuario(u3);
        libreria.agregarUsuario(u4);
        libreria.agregarUsuario(u5);
        libreria.agregarUsuario(u6);
        libreria.agregarUsuario(u7);
        libreria.agregarUsuario(u8);
        libreria.agregarUsuario(u9);
        libreria.agregarUsuario(u10);
        libreria.deshacer();
        System.out.println("");

        // Registrar préstamo
        System.out.println("Listado de préstamos registrados:");
        Prestamo p1 = new Prestamo(l2, u3);
        libreria.registrarPrestamo(p1);
        Prestamo p2 = new Prestamo(l1, u2);
        libreria.registrarPrestamo(p2);
        Prestamo p3 = new Prestamo(l4, u5);
        libreria.registrarPrestamo(p3);
        Prestamo p4 = new Prestamo(l5, u1);
        libreria.registrarPrestamo(p4);
        Prestamo p5 = new Prestamo(l3, u4);
        libreria.registrarPrestamo(p5);
        Prestamo p6 = new Prestamo(l6, u5);
        libreria.registrarPrestamo(p6);
        Prestamo p7 = new Prestamo(l7, u8);
        libreria.registrarPrestamo(p7);
        Prestamo p8 = new Prestamo(l8, u9);
        libreria.registrarPrestamo(p8);
        Prestamo p9 = new Prestamo(l9, u10);
        libreria.registrarPrestamo(p9);
        Prestamo p10 = new Prestamo(l10, u1);
        libreria.registrarPrestamo(p10);
        libreria.deshacer();
        System.out.println("");

        
        System.out.println("\nListado de usuarios:");
        for (Usuario u : libreria.getUsuarios()) {
            System.out.println("- " + u.getId() + ": " + u.getNombre());
        }

        System.out.println("\nListado de libros:");
        for (Libro l : libreria.listarLibros()) {
            System.out.println("- " + l.getId() + ": " + l.getTitulo() + " — " + l.getAutor());
        }

        System.out.println("\nBuscar libro por título 'Zelda':");
        for (Libro l : libreria.buscarPorTitulo("Zelda")) {
            System.out.println("- " + l.getId() + ": " + l.getTitulo());
        }

        System.out.println("\nBuscar usuario por ID = U3:");
        Usuario usuarioBuscado = libreria.buscarUsuarioPorId("U3");
        System.out.println("Usuario encontrado: " + (usuarioBuscado != null ? usuarioBuscado.getNombre() : "no encontrado"));


        System.out.println("\nModificar usuario ID = U3:");
        boolean modUsuario = libreria.modificarUsuarioPorId("U3", "Lord Vader");
        System.out.println("Modificado: " + modUsuario);
        u3 = libreria.buscarUsuarioPorId("U3");
        System.out.println("Nombre actual: " + (u3 != null ? u3.getNombre() : "no encontrado"));


        System.out.println("\nModificar libro ID = 5:");
        boolean modificado = libreria.modificarLibroPorId("5", "Spiderman", "Insomniac Games");
        System.out.println("Modificado: " + modificado);
        Libro lib1 = libreria.buscarPorId("5");
        System.out.println("Título actual: " + (lib1 != null ? lib1.getTitulo() : "no encontrado"));


        System.out.println("\nEliminar libro ID = 3:");
        boolean eliminado = libreria.eliminarLibroPorId("3");
        System.out.println("Eliminado: " + eliminado);
        libreria.deshacer();


        System.out.println("\nEliminar usuario ID = U4:");
        boolean eliminadoUsuario = libreria.eliminarUsuarioPorId("U4");
        System.out.println("Eliminado: " + eliminadoUsuario);
        libreria.deshacer();
        


        System.out.println("\nUsuarios finales:");
        for (Usuario u : libreria.getUsuarios()) {
            System.out.println("- " + u.getId() + ": " + u.getNombre());
        }

        System.out.println("\nLibros finales:");
        for (Libro l : libreria.getLibros()) {
            System.out.println("- " + l.getId() + ": " + l.getTitulo());
        }

    }
}