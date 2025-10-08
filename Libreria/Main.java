import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- SISTEMA DE BIBLIOTECA ---");
            System.out.println("1. Agregar libro");
            System.out.println("2. Buscar libro por título");
            System.out.println("3. Buscar libro por autor");
            System.out.println("4. Mostrar todos los libros");
            System.out.println("5. Registrar usuario");
            System.out.println("6. Mostrar todos los usuarios");
            System.out.println("7. Prestar libro");
            System.out.println("8. Devolver libro");
            System.out.println("9. Deshacer último préstamo");
            System.out.println("10. Reservar libro (si no está disponible)");
            System.out.println("11. Reporte de libros");
            System.out.println("12. Reporte de usuarios");
            System.out.println("13. Reporte de préstamos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, ingrese un número: ");
                scanner.next();
            }
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (option) {
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
                case 6:
                    if (library.getAllUsers().isEmpty()) {
                        System.out.println("No hay usuarios registrados.");
                    } else {
                        library.showAllUsersWithIterator();
                    }
                    break;
                case 7:
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
                case 8:
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
                case 9:
                    if (library.undoLastLoan()) {
                        System.out.println("Último préstamo deshecho correctamente.");
                    } else {
                        System.out.println("No hay préstamos para deshacer.");
                    }
                    break;
                case 10:
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
                case 11:
                    library.reportBooks();
                    break;
                case 12:
                    library.reportUsers();
                    break;
                case 13:
                    library.reportLoans();
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
}