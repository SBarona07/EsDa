package BibliotecaM2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Library {
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Loan> activeLoans = new ArrayList<Loan>();
    private LinkedList<String> operationHistory = new LinkedList<String>();
    private Stack<Operation> undoStack;
    private boolean suppressRecording = false;

    public Library(Stack<Operation> undoStack) {
        this.undoStack = undoStack;
    }

    public Book findBook(String isbn) {
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            if (b.getIsbn().equals(isbn))
                return b;
        }
        return null;
    }

    public void addBook(Book b) {
        books.add(b);
        if (!suppressRecording && undoStack != null)
            undoStack.push(new Operation(OperationType.REMOVE_BOOK, b.getIsbn(), b.getTitle(),
                    b.getAuthor() + "\t" + b.getCategory()));
    }

    public boolean updateBook(String isbn, String newTitle, String newAuthor, String newCategory) {
        Book b = findBook(isbn);
        if (b == null)
            return false;
        String oldTitle = b.getTitle();
        String oldAuthor = b.getAuthor();
        String oldCat = b.getCategory();

        b.setTitle(newTitle);
        b.setAuthor(newAuthor);
        b.setCategory(newCategory);

        if (!suppressRecording && undoStack != null)
            undoStack.push(new Operation(OperationType.UPDATE_BOOK, isbn, oldTitle, oldAuthor + "\t" + oldCat));
        return true;
    }

    public boolean removeBook(String isbn) {
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            if (b.getIsbn().equals(isbn)) {
                books.remove(i);
                if (!suppressRecording && undoStack != null)
                    undoStack.push(new Operation(OperationType.ADD_BOOK, isbn, b.getTitle(),
                            b.getAuthor() + "\t" + b.getCategory()));
                return true;
            }
        }
        return false;
    }

    public void listBooks() {
        System.out.println("=== Libros ===");
        Iterator<Book> it = books.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    public void searchByTitle(String q) {
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            if (b.getTitle().toLowerCase().indexOf(q.toLowerCase()) >= 0)
                System.out.println(b);
        }
    }

    public void searchByAuthor(String q) {
        Iterator<Book> it = books.iterator();
        while (it.hasNext()) {
            Book b = it.next();
            if (b.getAuthor().toLowerCase().indexOf(q.toLowerCase()) >= 0)
                System.out.println(b);
        }
    }

    public void searchByIsbn(String isbn) {
        Book b = findBook(isbn);
        System.out.println(b == null ? "No encontrado" : b.toString());
    }

    public User findUser(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id))
                return users.get(i);
        }
        return null;
    }

    public void registerUser(User u) {
        users.add(u);
        if (!suppressRecording && undoStack != null)
            undoStack.push(new Operation(OperationType.REMOVE_USER, u.getId(), u.getName(), null));
    }

    public boolean removeUser(String id) {
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u.getId().equals(id)) {
                users.remove(i);
                if (!suppressRecording && undoStack != null)
                    undoStack.push(new Operation(OperationType.REGISTER_USER, u.getId(), u.getName(), null));
                return true;
            }
        }
        return false;
    }

    public void listUsers() {
        System.out.println("=== Usuarios ===");
        Iterator<User> it = users.iterator();
        while (it.hasNext())
            System.out.println(it.next());
    }

    public void searchUserByName(String q) {
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u.getName().toLowerCase().indexOf(q.toLowerCase()) >= 0)
                System.out.println(u);
        }
    }

    public boolean borrow(String userId, String isbn) {
        User u = findUser(userId);
        Book b = findBook(isbn);
        if (u == null || b == null) {
            System.out.println("Usuario o libro no encontrado.");
            return false;
        }

            if (b.isAvailable()) {
            b.setAvailable(false);
            activeLoans.add(new Loan(userId, isbn));
            u.getLoanHistory().addFirst(isbn);
            operationHistory.addFirst("Prestar " + userId + " -> " + isbn);

            if (!suppressRecording && undoStack != null)
                undoStack.push(new Operation(OperationType.RETURN, userId, isbn, null));
            System.out.println("Préstamo OK.");
            return true;
        } else {
            Queue<String> q = b.getWaitingList();
            q.add(userId);
            operationHistory.addFirst("ENCOLAR_RESERVA " + userId + " " + isbn);
            if (!suppressRecording && undoStack != null)
                undoStack.push(new Operation(OperationType.ENQUEUE_RESERVATION, userId, isbn, null));
            System.out.println("Libro ocupado. Añadido a la lista de espera (pos " + q.size() + ").");
            return false;
        }
    }

    public boolean returnBook(String userId, String isbn) {
        int idx = -1;
        for (int i = 0; i < activeLoans.size(); i++) {
            Loan l = activeLoans.get(i);
            if (l.getUserId().equals(userId) && l.getIsbn().equals(isbn)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            System.out.println("Préstamo activo no encontrado.");
            return false;
        }

        activeLoans.remove(idx);
    operationHistory.addFirst("DEVOLVER " + userId + " <- " + isbn);
        if (!suppressRecording && undoStack != null)
            undoStack.push(new Operation(OperationType.BORROW, userId, isbn, null));

        Book b = findBook(isbn);
        if (b != null) {
            if (b.getWaitingList().isEmpty()) {
                b.setAvailable(true);
            } else {
                String nextUser = b.getWaitingList().poll();
                b.setAvailable(false);
                activeLoans.add(new Loan(nextUser, isbn));
                User nu = findUser(nextUser);
                if (nu != null)
                    nu.getLoanHistory().addFirst(isbn);
                operationHistory.addFirst("PRESTAMO_AUTO -> " + nextUser + " " + isbn);
            }
        }
        System.out.println("Devolución OK.");
        return true;
    }

    public void listActiveLoans() {
        System.out.println("=== Préstamos activos ===");
        Iterator<Loan> it = activeLoans.iterator();
        while (it.hasNext())
            System.out.println(it.next());
    }

    public void printHistory() {
        System.out.println("=== Historial de operaciones (más recientes primero) ===");
        Iterator<String> it = operationHistory.iterator();
        while (it.hasNext())
            System.out.println(it.next());
    }

    public String undoLast() {
        if (undoStack == null || undoStack.isEmpty()) return "Nada que deshacer.";
        Operation op = undoStack.pop();
        try {
            suppressRecording = true;
            OperationType t = op.getType();
            switch (t) {
                case REMOVE_BOOK:
                    this.removeBook(op.getA());
                    break;
                case ADD_BOOK:
                    String[] parts = op.getC() == null ? new String[] {"", ""} : op.getC().split("\t", 2);
                    String author = parts.length > 0 ? parts[0] : "";
                    String category = parts.length > 1 ? parts[1] : "";
                    this.addBook(new Book(op.getA(), op.getB(), author, category));
                    break;
                case UPDATE_BOOK:
                    String[] p2 = op.getC() == null ? new String[] {"", ""} : op.getC().split("\t", 2);
                    String oldAuthor = p2.length > 0 ? p2[0] : "";
                    String oldCat = p2.length > 1 ? p2[1] : "";
                    this.updateBook(op.getA(), op.getB(), oldAuthor, oldCat);
                    break;
                case REGISTER_USER:
                    this.registerUser(new User(op.getA(), op.getB()));
                    break;
                case REMOVE_USER:
                    this.removeUser(op.getA());
                    break;
                case BORROW:
                    this.borrow(op.getA(), op.getB());
                    break;
                case RETURN:
                    this.returnBook(op.getA(), op.getB());
                    break;
                case ENQUEUE_RESERVATION:
                    Book bk = this.findBook(op.getB());
                    if (bk != null) {
                        java.util.Queue<String> q = bk.getWaitingList();
                        java.util.Iterator<String> it = q.iterator();
                        while (it.hasNext()) {
                            if (it.next().equals(op.getA())) {
                                it.remove();
                                break;
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
            suppressRecording = false;
            return "Operación deshecha: " + op.getDescription();
        } catch (Exception e) {
            suppressRecording = false;
            return "Error al deshacer: " + e.getMessage();
        }
    }
}