package BibliotecaM2;

public class Operation {
    private OperationType type;
    private String a, b, c; // generic payload (A,B,C)

    public Operation(OperationType t, String a, String b, String c) {
        this.type = t;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public OperationType getType() {
        return type;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    // Descripción legible de la operación (para mostrar en undo)
    public String getDescription() {
        String label = "";
        if (type != null) {
            switch (type) {
                case ADD_BOOK:
                    label = "Añadir Libro";
                    break;
                case UPDATE_BOOK:
                    label = "Actualizar Libro";
                    break;
                case REMOVE_BOOK:
                    label = "Eliminar Libro";
                    break;
                case REGISTER_USER:
                    label = "Registrar Usuario";
                    break;
                case REMOVE_USER:
                    label = "Eliminar Usuario";
                    break;
                case BORROW:
                    label = "Prestar";
                    break;
                case RETURN:
                    label = "Devolver";
                    break;
                case ENQUEUE_RESERVATION:
                    label = "Encolar Reserva";
                    break;
                default:
                    label = type.name();
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(label);
        if (a != null && !a.isEmpty()) sb.append(" ").append(a);
        if (b != null && !b.isEmpty()) sb.append(" ").append(b);
        if (c != null && !c.isEmpty()) sb.append(" ").append(c);
        return sb.toString();
    }
}