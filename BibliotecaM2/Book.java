package BibliotecaM2;

import java.util.LinkedList;
import java.util.Queue;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String category; 
    private boolean available; 
    private Queue<String> waitingList;

    public Book(String isbn, String title, String author, String category) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = true;
        this.waitingList = new LinkedList<String>();
    }

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

    public boolean isAvailable() {
        return available;
    }

    public void setTitle(String t) {
        this.title = t;
    }

    public void setAuthor(String a) {
        this.author = a;
    }

    public void setCategory(String c) {
        this.category = c;
    }

    public void setAvailable(boolean a) {
        this.available = a;
    }

    public Queue<String> getWaitingList() {
        return waitingList;
    }

    @Override
    public String toString() {
    return "Libro [ISBN: " + isbn + " |Título: " + title + " |Autor: " + author + " |Categoría: " + category
        + " |Disponible: " + available + "]";
    }
}