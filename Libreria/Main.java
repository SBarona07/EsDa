/**
 * Clase principal que inicia la aplicación de gestión de biblioteca.
 * Crea una instancia de Library y LibraryUI, y lanza la interfaz de usuario.
 */
public class Main {
    public static void main(String[] args) {
        // Crea una nueva biblioteca vacía
        Library library = new Library();
        // Crea la interfaz de usuario asociada a la biblioteca
        LibraryUI ui = new LibraryUI(library);
        // Inicia la interacción con el usuario por consola
        ui.start();
    }
}