import java.util.Scanner;

// Clase principal que ejecuta el programa de la biblioteca
public class Main {

    /**
     * Método main.
     * Punto de entrada del programa. Muestra un menú interactivo para gestionar la biblioteca.
     * Permite añadir, buscar, eliminar y mostrar libros usando la clase Library.
     */
    public static void main(String[] args) {
        // Se crea una instancia de la biblioteca que almacenará objetos Book
        Library<Book> library = new Library<>();
        // Scanner para leer la entrada del usuario por consola
        Scanner scanner = new Scanner(System.in);
        // Variable para almacenar la opción elegida por el usuario
        String option = ""; // Inicialización para evitar el error

        // Bucle principal del menú, se repite hasta que el usuario elija salir
        do {
            try {
                // Se muestran las opciones del menú
                System.out.println("1. Añadir libro");
                System.out.println("2. Buscar libro");
                System.out.println("3. Eliminar libro");
                System.out.println("4. Mostrar libros");
                System.out.println("5. Buscar índice por título");
                System.out.println("0. Salir");
                System.out.print("Elige una opción: ");
                // Se lee la opción del usuario
                option = scanner.next();
                scanner.nextLine(); // limpiar buffer

                // Se evalúa la opción elegida
                switch (option) {
                    case "1":
                        // Añadir libro
                        System.out.print("ID: ");
                        String id = scanner.next();
                        scanner.nextLine(); // limpiar buffer
                        System.out.print("Título: ");
                        String title = scanner.nextLine();
                        System.out.print("Autor: ");
                        String author = scanner.nextLine();
                        // Se crea el libro y se añade a la biblioteca
                        Book book = new Book(id, title, author);
                        library.add(book);
                        System.out.println("Libro añadido.");
                        break;
                    case "2":
                        // Buscar libro por título
                        System.out.print("Título a buscar: ");
                        String searchTitle = scanner.nextLine();
                        System.out.println(library.bookSearch(searchTitle));
                        break;
                  case "3":
                        // Eliminar libro por índice con validación
                        System.out.print("Índice a eliminar: ");
                        String indexStr = scanner.next();
                        scanner.nextLine();
                        try {
                            int index = Integer.parseInt(indexStr);
                            Book b = library.getBook(index);
                            if (b != null) {
                                library.delete(indexStr);
                                System.out.println("Libro eliminado.");
                            } else {
                                System.out.println("No existe libro en ese índice.");
                            }
                        } catch (Exception e) {
                            System.out.println("Índice no válido.");
                        }
                        break;
                    case "4":
                        // Mostrar todos los libros con su índice usando el tamaño real
                        System.out.println("Libros en la biblioteca:");
                        for (int i = 0; i < 10; i++) { // Cambia 10 por library.size si tienes getter
                            Book b = library.getBook(i);
                            if (b != null) {
                                System.out.println("[" + i + "] " + b);
                            }
                        }
                        break;
                    case "5":
                        // Buscar índice por título
                        System.out.print("Título para buscar índice: ");
                        String tituloIndice = scanner.nextLine();
                        int idx = library.indexOf(tituloIndice);
                        if (idx != -1) {
                            System.out.println("Índice encontrado: " + idx);
                        } else {
                            System.out.println("Libro no encontrado.");
                        }
                        break;
                    case "0":
                        // Salir del programa
                        System.out.println("Saliendo...");
                        break;
                    default:
                        // Opción no válida
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                // Manejo de errores de entrada
                System.out.println("Entrada no válida. Intenta de nuevo.");
                scanner.nextLine(); // limpiar buffer en caso de error
            }
        } while (!option.equals("0")); // Condición de salida del bucle

        // Se cierra el scanner al finalizar el programa
        scanner.close();
    }
}