/**
 * Clase principal que inicia la aplicación de gestión de biblioteca.
 * Crea una instancia de Library y LibraryUI, y lanza la interfaz de usuario.
 */
public class Main {
    public static void main(String[] args) {
        // Crea una nueva biblioteca vacía.
        // Se utiliza la clase Library para gestionar la colección de libros.
        Library library = new Library();

        // Crea la interfaz de usuario asociada a la biblioteca.
        // LibraryUI permite la interacción por consola con el usuario.
        LibraryUI ui = new LibraryUI(library);

        // Inicia la interacción con el usuario por consola.
        // Se llama al método start() para mostrar el menú y procesar las opciones.
        ui.start();
    }
}