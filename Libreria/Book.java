public class Book {

    // Identificador único del libro
    private String id;
    // Título del libro
    private String title;
    // Autor del libro
    private String author;

    /**
     * Constructor de la clase Book.
     * Inicializa los atributos id, title y author con los valores proporcionados.
     * Permite crear una nueva instancia de libro con información específica.
     */
    public Book (String id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;
    }

    /**
     * Método getId.
     * Devuelve el identificador único del libro.
     * Útil para distinguir este libro de otros en la colección.
     */
    public String getId(){
        return id;
    }

    /**
     * Método getTitle.
     * Devuelve el título del libro.
     * Permite acceder al nombre del libro.
     */
    public String getTitle(){
        return title;
    }

    /**
     * Método getAuthor.
     * Devuelve el nombre del autor del libro.
     * Permite saber quién escribió el libro.
     */
    public String getAuthor(){
        return author;
    }

    /**
     * Método toString.
     * Devuelve una representación en texto del libro.
     * Útil para mostrar toda la información del libro en una sola línea.
     */
    public String toString(){
        return "Book [|id: " + id + " |Title: " + title + " |Author: " + author + "]";
    }
}