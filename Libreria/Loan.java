import java.time.LocalDate;

public class Loan {
    private String bookId;
    private String userId;
    private LocalDate loanDate;

    public Loan(String bookId, String userId, LocalDate loanDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.loanDate = loanDate;
    }

    public String getBookId() { return bookId; }
    public String getUserId() { return userId; }
    public LocalDate getLoanDate() { return loanDate; }

    @Override
    public String toString() {
        return "Loan [bookId: " + bookId + " |userId: " + userId + " |date: " + loanDate + "]";
    }
}