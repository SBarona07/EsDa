// Clase genérica que representa una biblioteca de libros
public class Library<T extends Book> {

    // Array para almacenar los libros
    private T[] books;
    // Número actual de libros en la biblioteca
    private int size;
    // Tamaño inicial del array de libros
    private static final int DEFAULT_SIZE = 10;

    /**
     * Constructor de la clase Library.
     * Inicializa el array de libros y el tamaño actual.
     */
    public Library() {
        // No se puede crear new T[], así que se usa Book[] y se castea
        books = (T[]) new Book[DEFAULT_SIZE];
        size = 0;
    }

    /**
     * Método add.
     * Añade un libro al final del array.
     * Si el array está lleno, aumenta su tamaño.
     */
    public void add(T book) {
        if (size == books.length) {
            increaseSize(); // Si está lleno, aumenta el tamaño del array
        }
        books[size++] = book;
    }

    /**
     * Método add (sobrecargado).
     * Añade o reemplaza un libro en una posición específica del array.
     */
    public void add(int index, T book) {
        books[index] = book;
    }

    /**
     * Método delete.
     * Elimina un libro por índice (recibido como String).
     * Desplaza los libros para llenar el hueco y elimina la referencia al último libro.
     */
    public void delete(String index) {
        int idx = Integer.parseInt(index);
        // Desplaza los libros para llenar el hueco
        for (int i = idx; i < size - 1; i++) {
            books[i] = books[i + 1];
        }
        books[--size] = null; // Elimina la referencia al último libro
    }

    /**
     * Método clear.
     * Elimina todos los libros de la biblioteca.
     * Deja el array vacío y reinicia el tamaño.
     */
    public void clear() {
        for (int i = 0; i < books.length; i++) {
            books[i] = null;
        }
        size = 0;
    }

    /**
     * Método getBook.
     * Devuelve el libro en la posición indicada del array.
     */
    public T getBook(int index) {
        return books[index];
    }

    /**
     * Método getSize.
     * Devuelve el número actual de libros en la biblioteca.
     */
    public int getSize() {
        return size;
    }

    /**
     * Método indexOf.
     * Busca el índice de un libro por su título.
     * Devuelve el índice si lo encuentra, o -1 si no existe.
     */
    public int indexOf(String title) {
        for (int i = 0; i < size; i++) {
            if (books[i] != null && books[i].getTitle().equalsIgnoreCase(title)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Método toString.
     * Devuelve una representación en texto de todos los libros.
     * Si no hay libros, indica que no se han agregado.
     */
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

    /**
     * Método bookSearch.
     * Busca un libro por título y devuelve el resultado como texto.
     * Si lo encuentra, indica el título; si no, devuelve "Not Found".
     */
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

    /**
     * Método increaseSize.
     * Aumenta el tamaño del array de libros cuando está lleno.
     * Copia los libros existentes al nuevo array más grande.
     */
    private void increaseSize() {
        T[] newArray = (T[]) new Book[books.length * 2];
        for (int i = 0; i < books.length; i++) {
            newArray[i] = books[i];
        }
        books = newArray;
    }
}