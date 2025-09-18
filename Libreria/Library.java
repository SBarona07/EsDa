import java.util.ArrayList; // Se importa ArrayList para almacenar los libros de forma dinámica.
import java.util.List;      // Se importa List para usar la interfaz y permitir flexibilidad.

/**
 * La clase Library representa una biblioteca que almacena una colección de libros.
 * Permite agregar, obtener, eliminar y buscar libros por título.
 */
public class Library {
    // Lista que almacena los libros de la biblioteca.
    // Se usa List para permitir cambiar la implementación si es necesario.
    private List<Book> books;

    /**
     * Constructor de la clase Library.
     * Inicializa la lista de libros como un ArrayList vacío.
     * Se usa ArrayList porque permite acceso rápido por índice y es eficiente para agregar al final.
     */
    public Library() {
        books = new ArrayList<>();
    }

    /**
     * Agrega un libro a la biblioteca.
     * @param book El libro a agregar.
     * Se usa el método add de ArrayList para añadir el libro al final de la lista.
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Obtiene el libro en la posición indicada.
     * @param index Índice del libro a obtener.
     * @return El libro en la posición dada, o null si el índice es inválido.
     * Se verifica que el índice sea válido para evitar errores.
     */
    public Book getBook(int index) {
        if (index >= 0 && index < books.size()) {
            return books.get(index); // Acceso rápido por índice gracias a ArrayList.
        }
        return null; // Retorna null si el índice está fuera de rango.
    }

    /**
     * Elimina el libro en la posición indicada.
     *  index Índice del libro a eliminar.
     *  true si el libro fue eliminado, false si el índice es inválido.
     * Se verifica el índice antes de eliminar para evitar excepciones.
     */
    public boolean deleteBook(int index) {
        if (index >= 0 && index < books.size()) {
            books.remove(index); // Elimina el libro en la posición dada.
            return true;
        }
        return false; // Retorna false si el índice es inválido.
    }

    /**
     * Busca el índice de un libro por su título (ignorando mayúsculas/minúsculas).
     *  title Título del libro a buscar.
     *  El índice del libro si se encuentra, -1 si no existe.
     * Se recorre la lista y se compara el título usando equalsIgnoreCase para mayor flexibilidad.
     */
    public int indexOf(String title) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                return i; // Retorna el índice si encuentra coincidencia.
            }
        }
        return -1; // Retorna -1 si no se encuentra el libro.
    }

    /**
     * Devuelve la lista completa de libros en la biblioteca.
     * @return Lista de libros.
     * Permite acceder a todos los libros almacenados.
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Devuelve la cantidad de libros en la biblioteca.
     * @return Número de libros almacenados.
     * Útil para saber el tamaño actual de la colección.
     */
    public int size() {
        return books.size();
    }
}