import java.util.*;

public class Library {
    private BookManager bookManager;
    private UserManager userManager;
    private LoanManager loanManager;

    public Library() {
        this.bookManager = new BookManager();
        this.userManager = new UserManager();
        this.loanManager = new LoanManager();
    }

    // --- Métodos de Libros ---
    public void addBook(Book book) {
        bookManager.addBook(book);
    }

    public boolean removeBook(String id) {
        return bookManager.removeBook(id);
    }

    public Book searchBookById(String id) {
        return bookManager.searchById(id);
    }

    public List<Book> searchBooksByTitle(String title) {
        return bookManager.searchByTitle(title);
    }

    public List<Book> searchBooksByAuthor(String author) {
        return bookManager.searchByAuthor(author);
    }

    public List<Book> getAllBooks() {
        return bookManager.getAllBooks();
    }

    // --- Métodos de Usuarios ---
    public void registerUser(User user) {
        userManager.addUser(user);
    }

    public User searchUserById(String id) {
        return userManager.searchById(id);
    }

    public List<User> getAllUsers() {
        return userManager.getAllUsers();
    }

    // --- Métodos de Préstamos ---
    public boolean loanBook(String bookId, String userId) {
        Book book = bookManager.searchById(bookId);
        User user = userManager.searchById(userId);
        if (book != null && user != null) {
            return loanManager.loanBook(book, user);
        }
        return false;
    }

    public boolean returnBook(String bookId, String userId) {
        return loanManager.returnBook(bookId, userId, bookManager.getAllBooks());
    }

    public List<Loan> getUserLoanHistory(String userId) {
        return userManager.getLoanHistory(userId);
    }

    public boolean undoLastLoan() {
        return loanManager.undoLastLoan(bookManager.getAllBooks(), userManager.getAllUsers());
    }

    public boolean reserveBook(String bookId, String userId) {
        Book book = bookManager.searchById(bookId);
        User user = userManager.searchById(userId);
        if (book != null && user != null) {
            return loanManager.reserveBook(book, user);
        }
        return false;
    }

    // --- Métodos con Iterator ---
    public void showAllBooksWithIterator() {
        bookManager.showAllWithIterator();
    }

    public void showAllUsersWithIterator() {
        userManager.showAllWithIterator();
    }

    public void showAllLoansWithIterator() {
        loanManager.showAllWithIterator();
    }

    // --- Reportes básicos ---
    public void reportBooks() {
        bookManager.report();
    }

    public void reportUsers() {
        userManager.report();
    }

    public void reportLoans() {
        loanManager.report();
    }
}