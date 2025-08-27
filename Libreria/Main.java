import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Library<Book> library = new Library<>();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("1. Añadir libro");
            System.out.println("2. Buscar libro");
            System.out.println("3. Eliminar libro");
            System.out.println("4. Mostrar libros");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (option) {
                case 1:
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // limpiar buffer
                    System.out.print("Título: ");
                    String title = scanner.nextLine();
                    System.out.print("Autor: ");
                    String author = scanner.nextLine();
                    Book book = new Book(id, title, author);
                    library.add(book);
                    System.out.println("Libro añadido.");
                    break;
                case 2:
                    System.out.print("Título a buscar: ");
                    String searchTitle = scanner.nextLine();
                    System.out.println(library.bookSearch(searchTitle));
                    break;
                case 3:
                    System.out.print("Índice a eliminar: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    library.delete(index);
                    System.out.println("Libro eliminado.");
                    break;
                case 4:
                    System.out.println("Libros en la biblioteca:");
                    System.out.println(library);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != 0);

        scanner.close();
    }
}
