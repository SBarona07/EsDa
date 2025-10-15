import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Gestión de Libros");
            System.out.println("2. Gestión de Usuarios");
            System.out.println("3. Gestión de Préstamos");
            System.out.println("4. Deshacer última acción");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, ingrese un número: ");
                scanner.next();
            }
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    menuLibros(scanner, library);
                    break;
                case 2:
                    menuUsuarios(scanner, library);
                    break;
                case 3:
                    menuPrestamos(scanner, library);
                    break;
                case 4:
                    if (library.undoLastAction()) {
                        System.out.println("Última acción deshecha correctamente.");
                    } else {
                        System.out.println("No hay acciones para deshacer.");
                    }
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (option != 0);
        scanner.close();
    }

    // Menú de gestión de libros
    public static void menuLibros(Scanner scanner, Library library) {
        int opt;
        do {
            System.out.println("\n--- GESTIÓN DE LIBROS ---");
            System.out.println("1. Agregar libro");
            System.out.println("2. Buscar libro por título");
            System.out.println("3. Buscar libro por autor");
            System.out.println("4. Mostrar todos los libros");
            System.out.println("5. Eliminar libro");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, ingrese un número: ");
                scanner.next();
            }
            opt = scanner.nextInt();
            scanner.nextLine();
            switch (opt) {
                case 1:
                    System.out.print("ID del libro: ");
                    String id = scanner.nextLine().trim();
                    if (id.isEmpty()) {
                        System.out.println("El ID no puede estar vacío ni contener solo espacios.");
                        break;
                    }
                    if (library.searchBookById(id) != null) {
                        System.out.println("Ya existe un libro con ese ID.");
                        break;
                    }
                    System.out.print("Título: ");
                    String title = scanner.nextLine().trim();
                    System.out.print("Autor: ");
                    String author = scanner.nextLine().trim();
                    System.out.print("Categoría: ");
                    String category = scanner.nextLine().trim();
                    if (title.isEmpty() || author.isEmpty() || category.isEmpty()) {
                        System.out.println("Ningún campo puede estar vacío ni contener solo espacios.");
                        break;
                    }
                    library.addBook(new Book(id, title, author, category));
                    System.out.println("Libro agregado.");
                    break;
                case 2:
                    System.out.print("Título a buscar: ");
                    String searchTitle = scanner.nextLine();
                    var booksByTitle = library.searchBooksByTitle(searchTitle);
                    if (booksByTitle.isEmpty()) {
                        System.out.println("No se encontraron libros con ese título.");
                    } else {
                        booksByTitle.forEach(System.out::println);
                    }
                    break;
                case 3:
                    System.out.print("Autor a buscar: ");
                    String searchAuthor = scanner.nextLine();
                    var booksByAuthor = library.searchBooksByAuthor(searchAuthor);
                    if (booksByAuthor.isEmpty()) {
                        System.out.println("No se encontraron libros de ese autor.");
                    } else {
                        booksByAuthor.forEach(System.out::println);
                    }
                    break;
                case 4:
                    if (library.getAllBooks().isEmpty()) {
                        System.out.println("No hay libros registrados.");
                    } else {
                        library.showAllBooksWithIterator();
                    }
                    break;
                case 5:
                    System.out.print("ID del libro a eliminar: ");
                    String deleteBookId = scanner.nextLine().trim();
                    if (library.deleteBook(deleteBookId)) {
                        System.out.println("Libro eliminado.");
                    } else {
                        System.out.println("No se pudo eliminar el libro. Verifique el ID.");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opt != 0);
    }

    // Menú de gestión de usuarios
    public static void menuUsuarios(Scanner scanner, Library library) {
        int opt;
        do {
            System.out.println("\n--- GESTIÓN DE USUARIOS ---");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Buscar usuario");
            System.out.println("3. Mostrar todos los usuarios");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, ingrese un número: ");
                scanner.next();
            }
            opt = scanner.nextInt();
            scanner.nextLine();
            switch (opt) {
                case 1:
                    System.out.print("ID del usuario: ");
                    String userId = scanner.nextLine().trim();
                    if (userId.isEmpty()) {
                        System.out.println("El ID no puede estar vacío ni contener solo espacios.");
                        break;
                    }
                    if (library.searchUserById(userId) != null) {
                        System.out.println("Ya existe un usuario con ese ID.");
                        break;
                    }
                    System.out.print("Nombre: ");
                    String name = scanner.nextLine().trim();
                    if (name.isEmpty()) {
                        System.out.println("El nombre no puede estar vacío ni contener solo espacios.");
                        break;
                    }
                    library.registerUser(new User(userId, name));
                    System.out.println("Usuario registrado.");
                    break;
                case 2:
                    System.out.print("ID del usuario a buscar: ");
                    String searchUserId = scanner.nextLine().trim();
                    User user = library.searchUserById(searchUserId);
                    if (user == null) {
                        System.out.println("No se encontró ningún usuario con ese ID.");
                    } else {
                        System.out.println(user);
                    }
                    break;
                case 3:
                    if (library.getAllUsers().isEmpty()) {
                        System.out.println("No hay usuarios registrados.");
                    } else {
                        library.showAllUsersWithIterator();
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opt != 0);
    }

    // Menú de gestión de préstamos
    public static void menuPrestamos(Scanner scanner, Library library) {
        int opt;
        do {
            System.out.println("\n--- GESTIÓN DE PRÉSTAMOS ---");
            System.out.println("1. Prestar libro");
            System.out.println("2. Devolver libro");
            System.out.println("3. Reservar libro");
            System.out.println("4. Reporte de préstamos");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, ingrese un número: ");
                scanner.next();
            }
            opt = scanner.nextInt();
            scanner.nextLine();
            switch (opt) {
                case 1:
                    System.out.print("ID del libro a prestar: ");
                    String loanBookId = scanner.nextLine().trim();
                    System.out.print("ID del usuario: ");
                    String loanUserId = scanner.nextLine().trim();
                    if (library.searchBookById(loanBookId) == null) {
                        System.out.println("No existe un libro con ese ID.");
                        break;
                    }
                    if (library.searchUserById(loanUserId) == null) {
                        System.out.println("No existe un usuario con ese ID.");
                        break;
                    }
                    if (library.loanBook(loanBookId, loanUserId)) {
                        System.out.println("Préstamo realizado.");
                    } else {
                        System.out.println("No se pudo realizar el préstamo. El libro puede no estar disponible.");
                    }
                    break;
                case 2:
                    System.out.print("ID del libro a devolver: ");
                    String returnBookId = scanner.nextLine().trim();
                    System.out.print("ID del usuario: ");
                    String returnUserId = scanner.nextLine().trim();
                    if (library.searchBookById(returnBookId) == null) {
                        System.out.println("No existe un libro con ese ID.");
                        break;
                    }
                    if (library.searchUserById(returnUserId) == null) {
                        System.out.println("No existe un usuario con ese ID.");
                        break;
                    }
                    if (library.returnBook(returnBookId, returnUserId)) {
                        System.out.println("Libro devuelto.");
                    } else {
                        System.out.println("No se pudo devolver el libro. Verifique los datos.");
                    }
                    break;
                case 3:
                    System.out.print("ID del libro a reservar: ");
                    String reserveBookId = scanner.nextLine().trim();
                    System.out.print("ID del usuario: ");
                    String reserveUserId = scanner.nextLine().trim();
                    if (library.reserveBook(reserveBookId, reserveUserId)) {
                        System.out.println("Reserva realizada. Será notificado cuando el libro esté disponible.");
                    } else {
                        System.out.println("No se pudo realizar la reserva. Verifique los datos o el libro está disponible.");
                    }
                    break;
                case 4:
                    library.reportLoans();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opt != 0);
    }
}