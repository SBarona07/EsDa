import java.util.LinkedList;

public class User {
    private String id;
    private String name;
    private LinkedList<Loan> loanHistory;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.loanHistory = new LinkedList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public LinkedList<Loan> getLoanHistory() { return loanHistory; }

    public void addLoanToHistory(Loan loan) {
        loanHistory.add(loan);
    }

    @Override
    public String toString() {
        return "User [id: " + id + " |Name: " + name + "]";
    }
}