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

    public Book searchById(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) return book;
        }
        return null;
    }

    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) result.add(book);
        }
        return result;
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
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
        for (Book book : books) {
            System.out.println(book);
        }
    }
}