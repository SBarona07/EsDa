package BibliotecaM2;

import java.util.LinkedList;

/**
 * Representa un usuario de la biblioteca.
 *
 * Campos habituales:
 * - id: identificador único del usuario (String), usado en búsquedas y en la waitingList.
 * - name: nombre para mostrar.
 * - (Opcional) información adicional: email, teléfono, número máximo de préstamos, estado (activo/bloqueado).
 *
 * Responsabilidades:
 * - Mantener datos inmutables o mutables mínimos (nombre modificable si se desea).
 * - No incluir aquí reglas de negocio (por ejemplo, límite de préstamos); esas reglas pertenecen a Library.
 *
 * Notas de diseño:
 * - Mantener equals/hashCode basados en id si se usa en colecciones tipo Set o Map.
 * - Si se requiere validación (por ejemplo formato de ID o longitud de nombre), puede añadirse en el constructor.
 */
public class User {
    private String id;
    private String name;
    private LinkedList<String> loanHistory = new LinkedList<String>();

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public LinkedList<String> getLoanHistory() {
        return loanHistory;
    }

    public String toString() {
        return "Usuario [id: " + id + " |Nombre: " + name + " |Historial de préstamos: " + loanHistory.size() + "]";
    }
}