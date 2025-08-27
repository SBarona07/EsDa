public class Library<T extends Book> {

    private T[] books;
    private int size;
    private static final int DEFAULT_SIZE = 10;

    public Library() {
        // no se puede crear new T[], así que toca usar Object[] y castear
        books = (T[]) new Book[DEFAULT_SIZE];
        size = 0;
    }

    public void add(T book) {
        if (size == books.length) {
            increaseSize();
        }
        books[size++] = book;
    }

    public void add(int index, T book) {
        books[index] = book;
    }

    public void delete(int index) {
        for (int i = index; i < size - 1; i++) {
            books[i] = books[i + 1];
        }
        books[--size] = null;
    }

    public void clear() {
        for (int i = 0; i < books.length; i++) {
            books[i] = null;
        }
        size = 0;
    }

    public T getBook(int index) {
        return books[index];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(books[i]).append(", ");
        }
        return sb.toString();
    }

    // Search by title
    public String bookSearch(String title) {
        String str = "Not Found";
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null && books[i].getTitle().equalsIgnoreCase(title)) {
                str = "Found: " + books[i].getTitle();
                break;
            }
        }
        return str;
    }

    private void increaseSize() {
        T[] newArray = (T[]) new Book[books.length * 2];
        for (int i = 0; i < books.length; i++) {
            newArray[i] = books[i];
        }
        books = newArray;
    }
}