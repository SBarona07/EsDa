import java.util.Queue;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * Sistema de triaje para gestionar las colas de pacientes según prioridad.
 */
public class TriageSystem {
    // Cola para pacientes críticos (prioridad 1)
    private Queue<Patient> criticalQueue = new ArrayDeque<>();
    // Cola para pacientes urgentes (prioridad 2)
    private Queue<Patient> urgentQueue = new LinkedList<>();
    // Cola para pacientes estándar (prioridad 3)
    private Queue<Patient> standardQueue = new LinkedList<>();
    // Estadísticas: total de pacientes atendidos
    private int totalServed = 0;
    // Estadísticas: tiempo total de espera acumulado (en minutos)
    private long totalWaitTime = 0;
    // Tiempo promedio de consulta por paciente (en minutos)
    private static final int AVG_CONSULT_TIME = 15;

    /**
     * Registra un paciente en la cola correspondiente según su prioridad.
     * @param patient Paciente a registrar
     * @return Posición del paciente en la cola de su prioridad
     */
    public int registerPatient(Patient patient) {
        switch(patient.getPriority()) {
            case 1: criticalQueue.offer(patient); return criticalQueue.size();
            case 2: urgentQueue.offer(patient); return urgentQueue.size();
            case 3: standardQueue.offer(patient); return standardQueue.size();
            default: throw new IllegalArgumentException("Prioridad inválida");
        }
    }

    /**
     * Llama al siguiente paciente según orden de prioridad.
     * @return El paciente llamado o null si no hay pacientes
     */
    public Patient callNextPatient() {
        Patient patient = null;
        if (!criticalQueue.isEmpty()) patient = criticalQueue.poll();
        else if (!urgentQueue.isEmpty()) patient = urgentQueue.poll();
        else if (!standardQueue.isEmpty()) patient = standardQueue.poll();

        if (patient != null) {
            int waitingTime = getWaitingTime(patient);
            totalWaitTime += waitingTime;
            totalServed++;
        }
        return patient;
    }

    /**
     * Calcula el tiempo de espera estimado para un paciente.
     * @param patient Paciente consultado
     * @return Tiempo estimado en minutos
     */
    public int getWaitingTime(Patient patient) {
        Queue<Patient> queue = getQueueForPriority(patient.getPriority());
        int position = 0;
        for (Patient p : queue) {
            if (p.equals(patient)) break;
            position++;
        }
        int higher = 0;
        if (patient.getPriority() > 1) higher += criticalQueue.size();
        if (patient.getPriority() > 2) higher += urgentQueue.size();
        return (higher + position) * AVG_CONSULT_TIME;
    }

    /**
     * Obtiene la cola correspondiente a una prioridad.
     */
    private Queue<Patient> getQueueForPriority(int priority) {
        if (priority == 1) return criticalQueue;
        if (priority == 2) return urgentQueue;
        return standardQueue;
    }

    /**
     * Muestra el estado actual de las colas y el próximo paciente a ser llamado.
     */
    public void displayStatus() {
        System.out.println("CRÍTICO:   " + criticalQueue.size() + " pacientes en espera");
        System.out.println("URGENTE:   " + urgentQueue.size() + " pacientes en espera");
        System.out.println("ESTÁNDAR:  " + standardQueue.size() + " pacientes en espera");
        System.out.print("Próximo a ser llamado: ");
        if (!criticalQueue.isEmpty()) System.out.println(criticalQueue.peek());
        else if (!urgentQueue.isEmpty()) System.out.println(urgentQueue.peek());
        else if (!standardQueue.isEmpty()) System.out.println(standardQueue.peek());
        else System.out.println("No hay pacientes en espera");
        System.out.println("Total pacientes en espera: " + getTotalPatientsWaiting());
    }

    /**
     * Elimina todos los pacientes por nombre de cualquiera de las colas.
     * @param name Nombre del paciente
     * @return cantidad eliminada
     */
    public int removePatient(String name) {
        int removedCount = 0;
        removedCount += removeAllFromQueue(criticalQueue, name);
        removedCount += removeAllFromQueue(urgentQueue, name);
        removedCount += removeAllFromQueue(standardQueue, name);
        return removedCount;
    }

    /**
     * Elimina todos los pacientes por nombre de una cola específica.
     */
    private int removeAllFromQueue(Queue<Patient> queue, String name) {
        int count = 0;
        Iterator<Patient> it = queue.iterator();
        while (it.hasNext()) {
            Patient p = it.next();
            if (p.getName().equalsIgnoreCase(name)) {
                it.remove();
                count++;
            }
        }
        return count;
    }

    /**
     * Muestra todos los pacientes en espera en cada cola, con tiempo estimado de espera.
     */
    public void displayAllPatients() {
        System.out.println("Cola CRÍTICO:");
        for (Patient p : criticalQueue)
            System.out.println("  " + p + " | Espera estimada: " + getWaitingTime(p) + " minutos");
        System.out.println("Cola URGENTE:");
        for (Patient p : urgentQueue)
            System.out.println("  " + p + " | Espera estimada: " + getWaitingTime(p) + " minutos");
        System.out.println("Cola ESTÁNDAR:");
        for (Patient p : standardQueue)
            System.out.println("  " + p + " | Espera estimada: " + getWaitingTime(p) + " minutos");
    }

    /**
     * Muestra estadísticas generales del sistema.
     */
    public void displayStatistics() {
        System.out.println("Total de pacientes atendidos: " + totalServed);
        System.out.println("Tiempo promedio de espera: " +
            (totalServed==0 ? 0 : (totalWaitTime/totalServed)) + " minutos");
    }

    /**
     * Devuelve el total de pacientes en espera en todas las colas.
     */
    public int getTotalPatientsWaiting() {
        return criticalQueue.size() + urgentQueue.size() + standardQueue.size();
    }
}