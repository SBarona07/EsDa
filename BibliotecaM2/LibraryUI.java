package BibliotecaM2;   

import java.util.Scanner;
import java.util.Stack;

/**
 * Interfaz de línea de comandos para la aplicación BibliotecaM2.
 *
 * Responsabilidades:
 * - Mostrar menús y leer entradas del usuario.
 * - Construir la instancia de Library y pasarle la pila de operaciones para undo.
 * - Delegar la lógica de dominio (añadir libros, préstamos, usuarios, etc.) a Library.
 *
 * Notas de diseño:
 * - La UI no debe contener lógica de negocio: solo valida entradas mínimas y llama a Library.
 * - La pila de operaciones (Stack<Operation>) se crea aquí y se inyecta en Library para centralizar
 *   el control sobre qué operaciones pueden deshacerse.
 */
public class LibraryUI {
    public static void main(String[] args) {
        // Scanner para leer la entrada estándar
        Scanner entrada = new Scanner(System.in);

        // Pila que almacena operaciones para poder deshacer (LIFO).
        // Se comparte con Library para que ésta registre las operaciones relevantes.
        Stack<Operation> pilaDeshacer = new Stack<Operation>();

        // Instancia de la lógica de la biblioteca; UI solo delega llamadas.
        Library lib = new Library(pilaDeshacer);

        boolean enEjecucion = true;
        while (enEjecucion) {
            System.out.println("\n==== Menú principal de la biblioteca ====");
            System.out.println("1) Gestión de libros");
            System.out.println("2) Gestión de usuarios");
            System.out.println("3) Gestión de préstamos");
            System.out.println("4) Deshacer última acción");
            System.out.println("0) Salir");
            System.out.print("Elija una opción: ");
            String opcion = entrada.nextLine().trim();

            switch (opcion) {
                case "1":
                    booksMenu(entrada, lib);
                    break;
                case "2":
                    usersMenu(entrada, lib);
                    break;
                case "3":
                    loansMenu(entrada, lib);
                    break;
                case "4": {
                    // Pide a Library que realice el undo de la última operación válida.
                    lib.undoLast();
                    System.out.println("Operación deshecha.");
                    break;
                }
                case "0":
                    enEjecucion = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        // Cerramos el scanner al terminar la ejecución para liberar recursos.
        entrada.close();
        System.out.println("¡Hasta pronto!");
    }

    /**
     * Menú de operaciones relacionadas con libros.
     * Solo formatea interacción con el usuario y delega en Library las operaciones.
     */
    private static void booksMenu(Scanner entrada, Library lib) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n-- Menú: Libros --");
            System.out.println("1) Añadir libro");
            System.out.println("2) Actualizar libro");
            System.out.println("3) Eliminar libro");
            System.out.println("4) Listar libros");
            System.out.println("5) Buscar por título");
            System.out.println("6) Buscar por autor");
            System.out.println("7) Buscar por ISBN");
            System.out.println("0) Volver");
            System.out.print("Opción: ");
            String o = entrada.nextLine().trim();

            switch (o) {
                case "1": {
                    // Lectura de campos mínimos para crear un libro.
                    String isbn = prompt(entrada, "ISBN: ");
                    String titulo = prompt(entrada, "Título: ");
                    String autor = prompt(entrada, "Autor: ");
                    String categoria = prompt(entrada, "Categoría: ");
                    // Se delega creación y registro a Library.
                    lib.addBook(new Book(isbn, titulo, autor, categoria));
                    System.out.println("Libro añadido.");
                    break;
                }
                case "2": {
                    // Actualización: la UI pide datos y Library realiza la búsqueda y modificación.
                    String isbn = prompt(entrada, "ISBN a actualizar: ");
                    String titulo = prompt(entrada, "Nuevo título: ");
                    String autor = prompt(entrada, "Nuevo autor: ");
                    String categoria = prompt(entrada, "Nueva categoría: ");
                    if (!lib.updateBook(isbn, titulo, autor, categoria))
                        System.out.println("No encontrado.");
                    else
                        System.out.println("Libro actualizado.");
                    break;
                }
                case "3": {
                    // Eliminación por ISBN; Library controla existencia y efectos secundarios.
                    String isbn = prompt(entrada, "ISBN a eliminar: ");
                    if (!lib.removeBook(isbn))
                        System.out.println("No encontrado.");
                    else
                        System.out.println("Libro eliminado.");
                    break;
                }
                case "4":
                    // Listado simple que imprime por consola.
                    lib.listBooks();
                    break;
                case "5": {
                    String q = prompt(entrada, "Título: ");
                    lib.searchByTitle(q);
                    break;
                }
                case "6": {
                    String q = prompt(entrada, "Autor: ");
                    lib.searchByAuthor(q);
                    break;
                }
                case "7": {
                    String q = prompt(entrada, "ISBN: ");
                    lib.searchByIsbn(q);
                    break;
                }
                case "0":
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    /**
     * Menú de gestión de usuarios.
     * La UI valida mínimamente y delega en Library las operaciones sobre usuarios.
     */
    private static void usersMenu(Scanner entrada, Library lib) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n-- Menú: Usuarios --");
            System.out.println("1) Registrar usuario");
            System.out.println("2) Eliminar usuario");
            System.out.println("3) Listar usuarios");
            System.out.println("4) Buscar por nombre");
            System.out.println("0) Volver");
            System.out.print("Opción: ");
            String o = entrada.nextLine().trim();

            switch (o) {
                case "1": {
                    String id = prompt(entrada, "ID de usuario: ");
                    String nombre = prompt(entrada, "Nombre: ");
                    lib.registerUser(new User(id, nombre));
                    System.out.println("Usuario registrado.");
                    break;
                }
                case "2": {
                    String id = prompt(entrada, "ID de usuario a eliminar: ");
                    if (!lib.removeUser(id))
                        System.out.println("No encontrado.");
                    else
                        System.out.println("Usuario eliminado.");
                    break;
                }
                case "3":
                    lib.listUsers();
                    break;
                case "4": {
                    String q = prompt(entrada, "Nombre: ");
                    lib.searchUserByName(q);
                    break;
                }
                case "0":
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    /**
     * Menú de préstamos: prestar, devolver, listar y mostrar historial.
     * Importante: la lógica de re-asignación al devolver (si hay waitingList) está en Library.
     */
    private static void loansMenu(Scanner entrada, Library lib) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n-- Menú: Préstamos --");
            System.out.println("1) Prestar libro");
            System.out.println("2) Devolver libro");
            System.out.println("3) Listar préstamos activos");
            System.out.println("4) Historial de préstamos");
            System.out.println("0) Volver");
            System.out.print("Opción: ");
            String o = entrada.nextLine().trim();

            switch (o) {
                case "1": {
                    String uid = prompt(entrada, "ID de usuario: ");
                    String isbn = prompt(entrada, "ISBN: ");
                    lib.borrow(uid, isbn);
                    break;
                }
                case "2": {
                    String uid = prompt(entrada, "ID de usuario: ");
                    String isbn = prompt(entrada, "ISBN: ");
                    lib.returnBook(uid, isbn);
                    break;
                }
                case "3":
                    lib.listActiveLoans();
                    break;
                case "4":
                    lib.printHistory();
                    break;
                case "0":
                    volver = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    // pequeño helper para unificar prompts
    private static String prompt(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }
}