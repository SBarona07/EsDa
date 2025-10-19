package BibliotecaM2;   

import java.util.Scanner;
import java.util.Stack;

public class LibraryUI {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Stack<Operation> pilaDeshacer = new Stack<Operation>();
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
        entrada.close();
        System.out.println("¡Hasta pronto!");
    }

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
                    String isbn = prompt(entrada, "ISBN: ");
                    String titulo = prompt(entrada, "Título: ");
                    String autor = prompt(entrada, "Autor: ");
                    String categoria = prompt(entrada, "Categoría: ");
                    lib.addBook(new Book(isbn, titulo, autor, categoria));
                    System.out.println("Libro añadido.");
                    break;
                }
                case "2": {
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
                    String isbn = prompt(entrada, "ISBN a eliminar: ");
                    if (!lib.removeBook(isbn))
                        System.out.println("No encontrado.");
                    else
                        System.out.println("Libro eliminado.");
                    break;
                }
                case "4":
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