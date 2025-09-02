import java.util.ArrayList;
import java.util.List;

/**
 * La clase Library representa una biblioteca que almacena una colección de libros.
 * Permite agregar, obtener, eliminar y buscar libros por título.
 */
public class Library {
    // Lista que almacena los libros de la biblioteca
    private List<Book> books;

    /**
     * Constructor de la clase Library.
     * Inicializa la lista de libros como un ArrayList vacío.
     */
    public Library() {
        books = new ArrayList<>();
    }

    /**
     * Agrega un libro a la biblioteca.
     * @param book El libro a agregar.
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Obtiene el libro en la posición indicada.
     * @param index Índice del libro a obtener.
     * @return El libro en la posición dada, o null si el índice es inválido.
     */
    public Book getBook(int index) {
        if (index >= 0 && index < books.size()) {
            return books.get(index);
        }
        return null;
    }

    /**
     * Elimina el libro en la posición indicada.
     * @param index Índice del libro a eliminar.
     * @return true si el libro fue eliminado, false si el índice es inválido.
     */
    public boolean deleteBook(int index) {
        if (index >= 0 && index < books.size()) {
            books.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Busca el índice de un libro por su título (ignorando mayúsculas/minúsculas).
     * @param title Título del libro a buscar.
     * @return El índice del libro si se encuentra, -1 si no existe.
     */
    public int indexOf(String title) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Devuelve la lista completa de libros en la biblioteca.
     * @return Lista de libros.
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Devuelve la cantidad de libros en la biblioteca.
     * @return Número de libros almacenados.
     */
    public int size() {
        return books.size();
    }
}