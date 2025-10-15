import java.util.Scanner;

/**
 * Sala de Emergencias: Sistema de Triaje
 * Menú interactivo para gestionar pacientes según prioridad.
 */
public class EmergencyRoom {
    public static void main(String[] args) {
        TriageSystem triage = new TriageSystem(); // Instancia del sistema de triaje
        Scanner sc = new Scanner(System.in);      // Para leer la entrada del usuario

        while (true) {
            // Menú principal
            System.out.println("\n========= SISTEMA DE TRIAJE SALA DE EMERGENCIAS =========");
            System.out.println("1. Registrar Nuevo Paciente");
            System.out.println("2. Llamar al Siguiente Paciente");
            System.out.println("3. Ver Pacientes en Espera");
            System.out.println("4. Paciente se Retira sin ser Atendido");
            System.out.println("5. Ver Estadísticas");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            int op = -1;
            while (op < 1 || op > 6) {
                try {
                    op = Integer.parseInt(sc.nextLine());
                    if (op < 1 || op > 6) System.out.print("Opción inválida. Elige entre 1 y 6: ");
                } catch (Exception e) {
                    System.out.print("Entrada no válida. Elige entre 1 y 6: ");
                }
            }

            switch (op) {
                case 1: // Registrar nuevo paciente
                    System.out.print("Nombre del paciente: ");
                    String name = sc.nextLine();
                    System.out.print("Síntomas: ");
                    String symptoms = sc.nextLine();
                    int prio = -1;
                    while (prio < 1 || prio > 3) {
                        System.out.println("Selecciona prioridad:\n  1 - CRÍTICO\n  2 - URGENTE\n  3 - ESTÁNDAR");
                        try {
                            prio = Integer.parseInt(sc.nextLine());
                            if (prio < 1 || prio > 3)
                                System.out.print("Prioridad no válida. Elige 1, 2 o 3: ");
                        } catch (Exception e) {
                            System.out.print("Entrada no válida. Elige 1, 2 o 3: ");
                        }
                    }
                    Patient p = new Patient(name, symptoms, prio);
                    int pos = triage.registerPatient(p);
                    int estWait = triage.getWaitingTime(p);
                    System.out.printf(
                        "\n✓ Paciente registrado: %s\n  Prioridad: %s\n  Posición en la cola: %d\n  Espera estimada: %d minutos\n",
                        name, (prio==1?"CRÍTICO":prio==2?"URGENTE":"ESTÁNDAR"), pos, estWait);
                    break;

                case 2: // Llamar al siguiente paciente según prioridad
                    Patient next = triage.callNextPatient();
                    if (next != null)
                        System.out.println("Atendiendo a: " + next);
                    else
                        System.out.println("No hay pacientes en espera");
                    break;

                case 3: // Mostrar todos los pacientes en espera
                    triage.displayAllPatients();
                    break;

                case 4: // Eliminar paciente que se retira sin ser atendido
                    System.out.print("Nombre del paciente a eliminar: ");
                    String removeName = sc.nextLine();
                    int removed = triage.removePatient(removeName);
                    if (removed == 0)
                        System.out.println("Paciente no encontrado.");
                    else if (removed == 1)
                        System.out.println("Paciente eliminado.");
                    else
                        System.out.println("¡Se eliminaron " + removed + " pacientes con ese nombre!");
                    break;

                case 5: // Mostrar estadísticas del sistema
                    triage.displayStatistics();
                    break;

                case 6: // Salir
                    System.out.println("Saliendo del sistema. ¡Hasta pronto!");
                    return;

                default:
                    System.out.println("Opción inválida.");
            }
            // Mostrar el estado actual de las colas
            triage.displayStatus();
        }
    }
}