/**
 * Clase Paciente para representar cada paciente en el sistema de triaje.
 */
public class Patient {
    // Nombre del paciente
    private String name;
    // Momento de llegada (timestamp)
    private long arrivalTime;
    // Prioridad: 1 = CRÍTICO, 2 = URGENTE, 3 = ESTÁNDAR
    private int priority;
    // Síntomas del paciente
    private String symptoms;

    /**
     * Constructor de la clase Paciente.
     * @param name Nombre del paciente
     * @param symptoms Síntomas descritos
     * @param priority Prioridad asignada
     */
    public Patient(String name, String symptoms, int priority) {
        this.name = name;
        this.symptoms = symptoms;
        this.priority = priority;
        this.arrivalTime = System.currentTimeMillis(); // Marca de tiempo de llegada
    }

    // Métodos getters
    public String getName() { return name; }
    public long getArrivalTime() { return arrivalTime; }
    public int getPriority() { return priority; }
    public String getSymptoms() { return symptoms; }

    /**
     * Devuelve una representación en texto del paciente.
     */
    @Override
    public String toString() {
        String prio;
        switch(priority) {
            case 1: prio = "CRÍTICO"; break;
            case 2: prio = "URGENTE"; break;
            case 3: prio = "ESTÁNDAR"; break;
            default: prio = "DESCONOCIDO";
        }
        return String.format("%s (Prioridad: %s, Síntomas: %s)", name, prio, symptoms);
    }

    /**
     * Dos pacientes se consideran iguales si tienen el mismo nombre y llegada.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Patient)) return false;
        Patient other = (Patient)obj;
        return this.name.equals(other.name) && this.arrivalTime == other.arrivalTime;
    }

    /**
     * HashCode para comparar objetos Patient.
     */
    @Override
    public int hashCode() {
        return name.hashCode() + (int)arrivalTime;
    }
}