public class Library<Book> {

    private Book[] books;
    private int size;
    private static final int DEFAULT_SIZE = 10;

    public Library() {
        books = (Book[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    public void add(Book Object) {
        if (size == books.length) {
            increaseSize();
        }
        books[size] = Object;
        size++;
    }

    public void add(int index, Book Object) {
        books[index] = Object;
    }

    public void delete(int index) {
        for (int i = index; i < books.length; i++) {
            if (i + 1 != size)
                books[i] = books[i + 1];
            else 
                books[i] = null;
        }
        size--;
    }

    public void clear() {
        for (int i = 0; i < books.length; i++) {
            books[i] = null;
        }
    }

    public Book getBook(int index) {
        return books[index];
    }

    public String toString() {
        String returnString = "";
        for (int i = 0; i < books.length; i++) {
            returnString += books[i] + " , ";
        }
        return returnString;
    }

    private void increaseSize() {
        Book[] newArray = (Book[]) new Object[books.length * 2];
        for (int i = 0; i < books.length; i++) {
            newArray[i] = books[i];
        }
        books = newArray;
    }
}