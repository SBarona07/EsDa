// Clase que representa un libro con id, título y autor
public class Book {

    // Identificador único del libro
    private String id;
    // Título del libro
    private String title;
    // Autor del libro
    private String author;

    // Constructor: inicializa los atributos del libro
    public Book (String id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;
    }

    // Devuelve el id del libro
    public String getId(){
        return id;
    }

    // Devuelve el título del libro
    public String getTitle(){
        return title;
    }

    // Devuelve el autor del libro
    public String getAuthor(){
        return author;
    }

    // Devuelve una representación en texto del libro
    public String toString(){
        return "Book [|id: " + id + " |Title: " + title + " |Author: " + author + "]";
    }
}