public class Tarea {
    private String nombre;
    private String prioridad;

    // Constructor
    public Tarea(String nombre, String prioridad){
        this.nombre = nombre;
        this.prioridad = prioridad;
    }
    // Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

}
