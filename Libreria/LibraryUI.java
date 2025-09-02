import java.util.Scanner;

/**
 * La clase LibraryUI proporciona una interfaz de usuario por consola
 * para interactuar con la biblioteca (Library). Permite al usuario
 * añadir, buscar, eliminar y mostrar libros, así como buscar el índice
 * de un libro por su título.
 */
public class LibraryUI {
    // Instancia de la biblioteca con la que se interactúa
    private Library library;
    // Scanner para leer la entrada del usuario por consola
    private Scanner scanner;

    /**
     * Constructor de la clase LibraryUI.
     * @param library La biblioteca a gestionar.
     */
    public LibraryUI(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Inicia el menú principal de la interfaz de usuario.
     * Permite al usuario seleccionar opciones hasta que decida salir.
     */
    public void start() {
        String option = "";
        do {
            // Muestra el menú de opciones
            System.out.println("1. Añadir libro");
            System.out.println("2. Buscar libro");
            System.out.println("3. Eliminar libro");
            System.out.println("4. Mostrar libros");
            System.out.println("5. Buscar índice por título");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            option = scanner.nextLine();

            // Procesa la opción seleccionada por el usuario
            switch (option) {
                case "1":
                    addBook();
                    break;
                case "2":
                    searchBook();
                    break;
                case "3":
                    deleteBook();
                    break;
                case "4":
                    showBooks();
                    break;
                case "5":
                    searchIndex();
                    break;
                case "0":
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (!option.equals("0"));
    }

    /**
     * Solicita los datos de un libro al usuario y lo añade a la biblioteca.
     */
    private void addBook() {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Título: ");
        String title = scanner.nextLine();
        System.out.print("Autor: ");
        String author = scanner.nextLine();
        Book book = new Book(id, title, author);
        library.addBook(book);
        System.out.println("Libro añadido.");
    }

    /**
     * Solicita un título al usuario y busca el libro correspondiente en la biblioteca.
     * Muestra el libro si se encuentra, o un mensaje si no existe.
     */
    private void searchBook() {
        System.out.print("Título a buscar: ");
        String title = scanner.nextLine();
        int idx = library.indexOf(title);
        if (idx != -1) {
            System.out.println(library.getBook(idx));
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    /**
     * Solicita un índice al usuario y elimina el libro correspondiente de la biblioteca.
     * Muestra un mensaje indicando el resultado de la operación.
     */
    private void deleteBook() {
        System.out.print("Índice a eliminar: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            if (library.deleteBook(index)) {
                System.out.println("Libro eliminado.");
            } else {
                System.out.println("No existe libro en ese índice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Índice no válido.");
        }
    }

    /**
     * Muestra todos los libros almacenados en la biblioteca junto con su índice.
     */
    private void showBooks() {
        System.out.println("Libros en la biblioteca:");
        for (int i = 0; i < library.size(); i++) {
            System.out.println("[" + i + "] " + library.getBook(i));
        }
    }

    /**
     * Solicita un título al usuario y muestra el índice del libro correspondiente,
     * o un mensaje si no existe.
     */
    private void searchIndex() {
        System.out.print("Título para buscar índice: ");
        String title = scanner.nextLine();
        int idx = library.indexOf(title);
        if (idx != -1) {
            System.out.println("Índice encontrado: " + idx);
        } else {
            System.out.println("Libro no encontrado.");
        }
    }
}