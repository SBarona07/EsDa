import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookManager {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        if (searchById(book.getId()) == null) {
            books.add(book);
        } else {
            System.out.println("Ya existe un libro con ese ID.");
        }
    }

    public boolean removeBook(String id) {
        return books.removeIf(book -> book.getId().equals(id));
    }

    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    public Book searchById(String id) {
        Iterator<Book> it = books.iterator();
        while (it.hasNext()) {
            Book book = it.next();
            if (book.getId().equals(id)) return book;
        }
        return null;
    }

    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        Iterator<Book> it = books.iterator();
        while (it.hasNext()) {
            Book book = it.next();
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) result.add(book);
        }
        return result;
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        Iterator<Book> it = books.iterator();
        while (it.hasNext()) {
            Book book = it.next();
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) result.add(book);
        }
        return result;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public void showAllWithIterator() {
        Iterator<Book> it = books.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public void report() {
        System.out.println("Reporte de libros:");
        Iterator<Book> it = books.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}