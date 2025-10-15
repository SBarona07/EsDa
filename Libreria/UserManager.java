import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserManager {
    private ArrayList<User> users = new ArrayList<>();

    public void addUser(User user) {
        if (searchById(user.getId()) == null) {
            users.add(user);
        } else {
            System.out.println("Ya existe un usuario con ese ID.");
        }
    }

    public User searchById(String id) {
        Iterator<User> it = users.iterator();
        while (it.hasNext()) {
            User user = it.next();
            if (user.getId().equals(id)) return user;
        }
        return null;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public List<Loan> getLoanHistory(String userId) {
        User user = searchById(userId);
        if (user != null) return user.getLoanHistory();
        return new ArrayList<>();
    }

    public void showAllWithIterator() {
        Iterator<User> it = users.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public void report() {
        System.out.println("Reporte de usuarios:");
        Iterator<User> it = users.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public boolean removeUser(User user) {
        return users.remove(user);
    }
}