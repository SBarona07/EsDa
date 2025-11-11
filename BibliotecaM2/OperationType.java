package BibliotecaM2;

/**
 * Tipos de operaciones que se registran para permitir deshacer (undo).
 *
 * - ADD_BOOK: se añadió un libro.
 * - UPDATE_BOOK: se actualizó metadatos de un libro.
 * - REMOVE_BOOK: se eliminó un libro.
 * - REGISTER_USER: se registró un usuario.
 * - REMOVE_USER: se eliminó un usuario.
 * - BORROW: se prestó un libro.
 * - RETURN: se devolvió un libro.
 * - ENQUEUE_RESERVATION: un usuario fue añadido a la waiting list (reserva).
 *
 * Estas constantes son usadas por Operation para indicar cómo deshacer la acción almacenada.
 */
public enum OperationType {
    ADD_BOOK, UPDATE_BOOK, REMOVE_BOOK,
    REGISTER_USER, REMOVE_USER,
    BORROW, RETURN, ENQUEUE_RESERVATION
}