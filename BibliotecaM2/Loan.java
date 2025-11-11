package BibliotecaM2;

/**
 * Representa un préstamo activo o histórico.
 *
 * Campos típicos:
 * - userId: ID del usuario que pidió el libro.
 * - isbn: ISBN del libro prestado.
 * - borrowDate: fecha de préstamo (String o java.time.LocalDate según la implementación).
 * - returnDate: fecha de devolución (nullable para préstamos activos).
 *
 * Responsabilidades:
 * - Contener los datos mínimos para identificar un préstamo y su historial.
 * - Proveer toString() para impresión legible en la UI y para debugging.
 *
 * Notas:
 * - Las fechas idealmente deberían manejarse con java.time.LocalDate/LocalDateTime.
 * - No incluir lógica de negocios compleja (vencimientos, multas) en esta clase; eso va en Library.
 */
public class Loan {
    private String userId;
    private String isbn;

    public Loan(String userId, String isbn) {
        this.userId = userId;
        this.isbn = isbn;
    }

    public String getUserId() {
        return userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String toString() {
        return "Préstamo [usuario: " + userId + " |ISBN: " + isbn + "]";
    }
}