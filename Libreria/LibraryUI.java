import java.util.Scanner; // Se importa Scanner para leer la entrada del usuario desde la consola.

/**
 * La clase LibraryUI proporciona una interfaz de usuario por consola
 * para interactuar con la biblioteca (Library). Permite al usuario
 * añadir, buscar, eliminar y mostrar libros, así como buscar el índice
 * de un libro por su título.
 */
public class LibraryUI {
    // Instancia de la biblioteca con la que se interactúa.
    // Se usa para acceder y modificar la colección de libros.
    private Library library;

    // Scanner para leer la entrada del usuario por consola.
    // Permite recibir datos dinámicamente durante la ejecución.
    private Scanner scanner;

    /**
     * Constructor de la clase LibraryUI.
     * @param library La biblioteca a gestionar.
     * Se recibe una instancia de Library para que la interfaz pueda operar sobre ella.
     */
    public LibraryUI(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in); // Se inicializa Scanner para leer datos del usuario.
    }

    /**
     * Inicia el menú principal de la interfaz de usuario.
     * Permite al usuario seleccionar opciones hasta que decida salir.
     * Se utiliza un bucle do-while para mantener el menú activo.
     */
    public void start() {
        String option = "";
        do {
            // Muestra el menú de opciones.
            // Se utiliza System.out.println para mostrar las opciones disponibles.
            System.out.println("1. Añadir libro");
            System.out.println("2. Buscar libro");
            System.out.println("3. Eliminar libro");
            System.out.println("4. Mostrar libros");
            System.out.println("5. Buscar índice por título");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            option = scanner.nextLine().trim(); // Se lee la opción elegida por el usuario.

            // Procesa la opción seleccionada por el usuario usando switch-case.
            // Permite ejecutar la función correspondiente según la opción.
            switch (option) {
                case "1":
                    addBook(); // Añade un libro a la biblioteca.
                    break;
                case "2":
                    searchBook(); // Busca un libro por título.
                    break;
                case "3":
                    deleteBook(); // Elimina un libro por índice.
                    break;
                case "4":
                    showBooks(); // Muestra todos los libros con su índice.
                    break;
                case "5":
                    searchIndex(); // Busca el índice de un libro por título.
                    break;
                case "0":
                    System.out.println("Saliendo..."); // Mensaje de salida.
                    break;
                default:
                    System.out.println("Opción no válida."); // Control de opciones incorrectas.
            }
        } while (!option.equals("0")); // El menú se repite hasta que el usuario elige salir.
    }

    /**
     * Solicita los datos de un libro al usuario y lo añade a la biblioteca.
     * Se usa para crear nuevos libros a partir de la entrada del usuario.
     */
    private void addBook() {
        System.out.print("ID: ");
        String id = scanner.nextLine(); // Se pide el ID del libro.
        System.out.print("Título: ");
        String title = scanner.nextLine(); // Se pide el título.
        System.out.print("Autor: ");
        String author = scanner.nextLine(); // Se pide el autor.
        Book book = new Book(id, title, author); // Se crea una instancia de Book.
        library.addBook(book); // Se añade el libro a la biblioteca.
        System.out.println("Libro añadido."); // Mensaje de confirmación.
    }

    /**
     * Solicita un título al usuario y busca el libro correspondiente en la biblioteca.
     * Muestra el libro si se encuentra, o un mensaje si no existe.
     * Se utiliza para localizar libros por su título.
     */
    private void searchBook() {
        System.out.print("Título a buscar: ");
        String title = scanner.nextLine(); // Se pide el título a buscar.
        int idx = library.indexOf(title); // Se busca el índice del libro.
        if (idx != -1) {
            System.out.println(library.getBook(idx)); // Se muestra el libro encontrado.
        } else {
            System.out.println("Libro no encontrado."); // Mensaje si no existe.
        }
    }

    /**
     * Solicita un índice al usuario y elimina el libro correspondiente de la biblioteca.
     * Muestra un mensaje indicando el resultado de la operación.
     * Se usa para borrar libros por su posición en la lista.
     */
    private void deleteBook() {
        System.out.print("Índice a eliminar: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()); // Se convierte la entrada a entero.
            if (library.deleteBook(index)) {
                System.out.println("Libro eliminado."); // Mensaje si se elimina correctamente.
            } else {
                System.out.println("No existe libro en ese índice."); // Mensaje si el índice es inválido.
            }
        } catch (NumberFormatException e) {
            System.out.println("Índice no válido."); // Control de errores si la entrada no es un número.
        }
    }

    /**
     * Muestra todos los libros almacenados en la biblioteca junto con su índice.
     * Permite al usuario ver el contenido completo de la biblioteca.
     */
    private void showBooks() {
        System.out.println("Libros en la biblioteca:");
        for (int i = 0; i < library.size(); i++) {
            System.out.println("[" + i + "] " + library.getBook(i)); // Se muestra cada libro con su índice.
        }
    }

    /**
     * Solicita un título al usuario y muestra el índice del libro correspondiente,
     * o un mensaje si no existe.
     * Se usa para encontrar la posición de un libro por su título.
     */
    private void searchIndex() {
        System.out.print("Título para buscar índice: ");
        String title = scanner.nextLine(); // Se pide el título.
        int idx = library.indexOf(title); // Se busca el índice.
        if (idx != -1) {
            System.out.println("Índice encontrado: " + idx); // Se muestra el índice si existe.
        } else {
            System.out.println("Libro no encontrado."); // Mensaje si no existe.
        }
    }
    public static void main(String[] args) {
        Library library = new Library(); // Se crea una instancia de Library.
        LibraryUI ui = new LibraryUI(library); // Se crea la interfaz de usuario con la biblioteca.
        ui.start(); // Se inicia la interfaz de usuario.
    }
}