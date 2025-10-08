import java.util.*;

public class LoanManager {
    private Stack<Loan> loanStack = new Stack<>();
    private Queue<Loan> loanQueue = new LinkedList<>();
    private Map<String, Queue<User>> reservas = new HashMap<>();

    // Préstamo de libro
    public boolean loanBook(Book book, User user) {
        if (book == null || user == null) return false;
        if (!book.isAvailable()) {
            // Si no está disponible, agregar a la cola de reservas
            reserveBook(book, user);
            return false;
        }
        Loan loan = new Loan(book.getId(), user.getId(), java.time.LocalDate.now());
        loanStack.push(loan);
        loanQueue.offer(loan);
        book.setAvailable(false);
        user.addLoanToHistory(loan);
        return true;
    }

    // Devolver libro
    public boolean returnBook(String bookId, String userId, List<Book> books) {
        for (Loan loan : loanStack) {
            if (loan.getBookId().equals(bookId) && loan.getUserId().equals(userId)) {
                // Marcar libro como disponible
                for (Book book : books) {
                    if (book.getId().equals(bookId)) {
                        book.setAvailable(true);
                        // Si hay reservas, prestar al siguiente usuario en la cola
                        Queue<User> cola = reservas.get(bookId);
                        if (cola != null && !cola.isEmpty()) {
                            User nextUser = cola.poll();
                            loanBook(book, nextUser);
                            System.out.println("Libro reservado entregado automáticamente a: " + nextUser.getName());
                        }
                        break;
                    }
                }
                loanStack.remove(loan);
                return true;
            }
        }
        return false;
    }

    // Deshacer último préstamo
    public boolean undoLastLoan(List<Book> books, List<User> users) {
        if (!loanStack.isEmpty()) {
            Loan lastLoan = loanStack.pop();
            // Marcar libro como disponible
            for (Book book : books) {
                if (book.getId().equals(lastLoan.getBookId())) {
                    book.setAvailable(true);
                    // Si hay reservas, prestar al siguiente usuario en la cola
                    Queue<User> cola = reservas.get(book.getId());
                    if (cola != null && !cola.isEmpty()) {
                        User nextUser = cola.poll();
                        loanBook(book, nextUser);
                        System.out.println("Libro reservado entregado automáticamente a: " + nextUser.getName());
                    }
                    break;
                }
            }
            // Eliminar préstamo del historial del usuario
            for (User user : users) {
                if (user.getId().equals(lastLoan.getUserId())) {
                    user.getLoanHistory().remove(lastLoan);
                    break;
                }
            }
            return true;
        }
        return false;
    }

    // Reservar libro
    public boolean reserveBook(Book book, User user) {
        if (book.isAvailable()) return false; // No reservar si está disponible
        reservas.putIfAbsent(book.getId(), new LinkedList<>());
        Queue<User> cola = reservas.get(book.getId());
        if (!cola.contains(user)) {
            cola.offer(user);
            return true;
        }
        return false;
    }

    public void showAllWithIterator() {
        Iterator<Loan> it = loanStack.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public void report() {
        System.out.println("Reporte de préstamos:");
        for (Loan loan : loanStack) {
            System.out.println(loan);
        }
    }
}