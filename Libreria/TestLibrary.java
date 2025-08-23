public class TestLibrary {
    public static void main(String[] args) {

        Library library = new Library();

        library.add(new Book(1, "soledad1", "Gabriel"));
        library.add(new Book(2, "soledad2", "Gabriel"));
        library.add(new Book(3, "soledad3", "Gabriel"));
        library.add(new Book(4, "soledad4", "Gabriel"));
        library.add(new Book(5, "soledad5", "Gabriel"));
        library.add(new Book(6, "soledad6", "Gabriel"));
        library.add(new Book(7, "soledad7", "Gabriel"));
        library.add(new Book(8, "soledad8", "Gabriel"));
        library.add(new Book(9, "Zoraya9", "Catalina"));
        library.add(new Book(10, "soledad10", "Gabriel"));

        library.delete(4);

        System.out.println(library);

        System.out.println(library.getBook(7));

        library.add(9, new Book(101, "Moreno", "Barona"));
                  
        System.out.println();
        
        System.out.println(library);

        //clean library
        library.clear();
        System.out.println(library);
    }
}