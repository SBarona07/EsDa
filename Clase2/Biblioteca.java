import java.util.Scanner;

public class Biblioteca {
    

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.libros[0] = new Libro("El principito", "Saint-Exupéry", 1943);
        biblioteca.libros[1] = new Libro("El señor de los anillos", "Tolkien", 1954);
        biblioteca.libros[2] = new Libro("Cien años de soledad", "García Márquez", 1967);
        biblioteca.libros[3] = new Libro("Don Quijote de la Mancha", "Cervantes", 1605);
        biblioteca.libros[4] = new Libro("Orgullo y prejuicio", "Austen", 1813);
        biblioteca.libros[5] = new Libro("1984", "Orwell", 1949);
        biblioteca.libros[6] = new Libro("El gran Gatsby", "Fitzgerald", 1925);
        biblioteca.libros[7] = new Libro("Crimen y castigo", "Dostoievski", 1866);
        biblioteca.libros[8] = new Libro("El retrato de Dorian Gray", "Wilde", 1890);
        biblioteca.libros[9] = new Libro("Moby Dick", "Melville", 1851);

        System.out.println("Libros en la biblioteca:");
        for (int i = 0; i < biblioteca.libros.length; i++) {
            if (biblioteca.libros[i] != null) {
                System.out.println(biblioteca.libros[i].getTitulo());
            }
        }

        Scanner scanner = new Scanner(System.in);

        // --- preguntar y añadir libros primero ---
        System.out.print("\n¿Deseas agregar un libro? (s/n): ");
        String resp = scanner.nextLine().trim();
        while (resp.equalsIgnoreCase("s") || resp.equalsIgnoreCase("si")) {
            System.out.print("Título: ");
            String nuevoTitulo = scanner.nextLine().trim();
            System.out.print("Autor: ");
            String nuevoAutor = scanner.nextLine().trim();
            System.out.print("Año: ");
            int nuevoAnio = 0;
            try {
                nuevoAnio = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Año inválido, se guardará 0.");
            }

            boolean agregado = biblioteca.addLibro(new Libro(nuevoTitulo, nuevoAutor, nuevoAnio));
            if (agregado) {
                System.out.println("Libro agregado correctamente.");
            } else {
                System.out.println("No se pudo agregar: la biblioteca está llena.");
                break;
            }

            System.out.print("\n¿Deseas agregar otro libro? (s/n): ");
            resp = scanner.nextLine().trim();
        }

        // --- luego preguntar por búsqueda ---
        System.out.print("\nIngrese el título a buscar: ");
        String titulo = scanner.nextLine().trim();

        Libro encontrado = biblioteca.searchByTitulo(titulo);
        if (encontrado != null) {
            System.out.println("Libro encontrado: " + encontrado.getTitulo() +
                    " — " + encontrado.getAutor() + " (" + encontrado.getAnio() + ")");
        } else {
            System.out.println("No se encontró el libro: " + titulo);
        }

        scanner.close();
    }

    public Libro searchByTitulo(String titulo) {
        for (int i = 0; i < libros.length; i++) {
            if (libros[i] != null && libros[i].getTitulo().equalsIgnoreCase(titulo)) {
                return libros[i];
            }
        }
        return null;
    }

    public boolean addLibro(Libro nuevoLibro) {
        for (int i = 0; i < libros.length; i++) {
            if (libros[i] == null) {
                libros[i] = nuevoLibro;
                return true;
            }
        }
        return false;
    }
}

class Libro {
    private String titulo;
    private String autor;
    private int anio;

    public Libro(String titulo, String autor, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnio() {
        return anio;
    }
}