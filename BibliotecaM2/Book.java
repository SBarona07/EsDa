package BibliotecaM2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Representa un libro en la biblioteca.
 *
 * Campos:
 * - isbn: identificador único del libro (se asume único por ejemplar lógico).
 * - title, author, category: metadatos descriptivos.
 * - available: indica si el libro está disponible para préstamo en este momento.
 * - waitingList: cola FIFO de IDs de usuarios en espera para este libro.
 *
 * Notas:
 * - La clase mantiene la lista de espera como Queue<String> con los IDs de usuario.
 * - El campo 'available' es una simplificación que asume una copia por ISBN.
 *   Si se desean múltiples copias, conviene cambiar el modelo para contar ejemplares.
 */
public class Book {
    private String isbn;
    private String title;
    private String author;
    private String category; 
    private boolean available; 
    private Queue<String> waitingList;

    /**
     * Constructor principal.
     *
     * @param isbn      ISBN o identificador del libro.
     * @param title     Título del libro.
     * @param author    Autor del libro.
     * @param category  Categoría o género del libro.
     */
    public Book(String isbn, String title, String author, String category) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
        // Por defecto un libro recién creado está disponible.
        this.available = true;
        // Cola FIFO para gestionar la waiting list de usuarios.
        this.waitingList = new LinkedList<String>();
    }

    /* ---------- Getters ---------- */

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    /**
     * Indica si el libro está disponible para préstamo inmediato.
     * Si se cambia el modelo a múltiples copias, este método debe ajustarse.
     */
    public boolean isAvailable() {
        return available;
    }

    /* ---------- Setters / Mutadores ---------- */

    public void setTitle(String t) {
        this.title = t;
    }

    public void setAuthor(String a) {
        this.author = a;
    }

    public void setCategory(String c) {
        this.category = c;
    }

    /**
     * Marcar disponibilidad (true = disponible, false = prestado).
     * Library debe encargarse de mantener la coherencia entre available y activeLoans.
     */
    public void setAvailable(boolean a) {
        this.available = a;
    }

    /**
     * Exponer la cola de espera para operaciones de Library.
     * Se retorna la referencia para permitir encolar/desencolar usuarios.
     */
    public Queue<String> getWaitingList() {
        return waitingList;
    }

    @Override
    public String toString() {
    return "Libro [ISBN: " + isbn + " |Título: " + title + " |Autor: " + author + " |Categoría: " + category
        + " |Disponible: " + available + "]";
    }
}