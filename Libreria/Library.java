// Clase genérica que representa una biblioteca de libros
public class Library<T extends Book> {

    // Array para almacenar los libros
    private T[] books;
    // Número actual de libros en la biblioteca
    private int size;
    // Tamaño inicial del array de libros
    private static final int DEFAULT_SIZE = 10;

    // Constructor: inicializa el array de libros y el tamaño
    public Library() {
        // No se puede crear new T[], así que se usa Book[] y se castea
        books = (T[]) new Book[DEFAULT_SIZE];
        size = 0;
    }

    // Añade un libro al final del array
    public void add(T book) {
        if (size == books.length) {
            increaseSize(); // Si está lleno, aumenta el tamaño del array
        }
        books[size++] = book;
    }

    // Añade o reemplaza un libro en una posición específica
    public void add(int index, T book) {
        books[index] = book;
    }

    // Elimina un libro por índice (recibido como String)
    public void delete(String index) {
        int idx = Integer.parseInt(index);
        // Desplaza los libros para llenar el hueco
        for (int i = idx; i < size - 1; i++) {
            books[i] = books[i + 1];
        }
        books[--size] = null; // Elimina la referencia al último libro
    }

    // Elimina todos los libros de la biblioteca
    public void clear() {
        for (int i = 0; i < books.length; i++) {
            books[i] = null;
        }
        size = 0;
    }

    // Devuelve el libro en la posición indicada
    public T getBook(int index) {
        return books[index];
    }

    // Devuelve una representación en texto de todos los libros
    public String toString() {
        if (size == 0) {
            return "No se han agregado libros.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(books[i]).append("\n ");
        }
        return sb.toString();
    }

    // Busca un libro por título y devuelve el resultado
    public String bookSearch(String title) {
        String str = "Not Found";
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null && books[i].getTitle().equalsIgnoreCase(title)) {
                str = "Found: " + books[i].getTitle();
                break;
            }
        }
        return str;
    }

    // Aumenta el tamaño del array de libros cuando está lleno
    private void increaseSize() {
        T[] newArray = (T[]) new Book[books.length * 2];
        for (int i = 0; i < books.length; i++) {
            newArray[i] = books[i];
        }
        books = newArray;
    }
}