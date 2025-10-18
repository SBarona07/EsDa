package BibliotecaSimple;

public class Loan {
    private String userId;
    private String isbn;

    public Loan(String userId, String isbn) {
        this.userId = userId;
        this.isbn = isbn;
    }

    public String getUserId() {
        return userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String toString() {
        return "Loan [userId: " + userId + " |isbn: " + isbn + "]";
    }
}