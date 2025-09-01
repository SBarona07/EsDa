public class TestLibrary {
    public static void main(String[] args) {

        Library<Book> library = new Library<>();

        library.add(new Book("100", "Perez", "Garcia"));
        library.add(new Book("101", "Lopez", "Martinez"));
        library.add(new Book("102", "Gomez", "Sanchez"));
        library.add(new Book("103", "Moreno", "Barona"));
        library.add(new Book("104", "Diaz", "Fernandez"));
        library.add(new Book("105", "Alvarez", "Jimenez"));
        library.add(new Book("106", "Romero", "Ruiz"));
        library.add(new Book("107", "Serrano", "Hernandez"));
        library.add(new Book("108", "Vega", "Navarro"));
        library.add(new Book("109", "Cruz", "Torres"));
        library.add(new Book("110", "Ramos", "Dominguez"));

        library.delete("4");

        System.out.println(library);

        System.out.println(library.getBook(7));

        library.add(new Book("101", "Moreno", "Barona"));
                  
        System.out.println();
        
        System.out.println(library);

        //Search
        System.out.println(library.bookSearch("Perez"));

        //clean library
        library.clear();
        System.out.println(library);
    }
}