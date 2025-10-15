import java.util.*;

public class Library {
    private BookManager bookManager;
    private UserManager userManager;
    private LoanManager loanManager;
    private Stack<Action> actionHistory = new Stack<>(); // Historial de acciones

    public Library() {
        this.bookManager = new BookManager();
        this.userManager = new UserManager();
        this.loanManager = new LoanManager();
    }

    // --- Interfaz y clases de acciones ---
    private interface Action { void undo(); }

    private class AddBookAction implements Action {
        private Book book;
        AddBookAction(Book book) { this.book = book; }
        public void undo() { bookManager.removeBook(book); }
    }

    private class AddUserAction implements Action {
        private User user;
        AddUserAction(User user) { this.user = user; }
        public void undo() { userManager.removeUser(user); }
    }

    private class LoanAction implements Action {
        private Book book;
        private User user;
        LoanAction(Book book, User user) { this.book = book; this.user = user; }
        public void undo() { loanManager.returnBook(book.getId(), user.getId(), bookManager.getAllBooks()); }
    }

    private class ReturnAction implements Action {
        private Book book;
        private User user;
        ReturnAction(Book book, User user) { this.book = book; this.user = user; }
        public void undo() { loanManager.loanBook(book, user); }
    }

    private class ReserveAction implements Action {
        private Book book;
        private User user;
        ReserveAction(Book book, User user) { this.book = book; this.user = user; }
        public void undo() { loanManager.cancelReservation(book, user); }
    }

    // --- Métodos de Libros ---
    public void addBook(Book book) {
        bookManager.addBook(book);
        actionHistory.push(new AddBookAction(book));
    }

    public boolean deleteBook(String id) {
        Book book = searchBookById(id);
        if (book != null) {
            return bookManager.removeBook(book);
        }
        return false;
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
        actionHistory.push(new AddUserAction(user));
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
            boolean result = loanManager.loanBook(book, user);
            if (result) actionHistory.push(new LoanAction(book, user));
            return result;
        }
        return false;
    }

    public boolean returnBook(String bookId, String userId) {
        Book book = bookManager.searchById(bookId);
        User user = userManager.searchById(userId);
        if (book != null && user != null) {
            boolean result = loanManager.returnBook(bookId, userId, bookManager.getAllBooks());
            if (result) actionHistory.push(new ReturnAction(book, user));
            return result;
        }
        return false;
    }

    public List<Loan> getUserLoanHistory(String userId) {
        return userManager.getLoanHistory(userId);
    }

    // Nuevo método para deshacer la última acción (alta o transacción)
    public boolean undoLastAction() {
        if (!actionHistory.isEmpty()) {
            actionHistory.pop().undo();
            return true;
        }
        return false;
    }

    public boolean reserveBook(String bookId, String userId) {
        Book book = bookManager.searchById(bookId);
        User user = userManager.searchById(userId);
        if (book != null && user != null) {
            boolean result = loanManager.reserveBook(book, user);
            if (result) actionHistory.push(new ReserveAction(book, user));
            return result;
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