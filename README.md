# EsDa
Estructura de Datos

## Proyecto Libreria

La clase [`Library`](Libreria/Library.java) representa una biblioteca que almacena una colección de libros y ofrece métodos para gestionar dicha colección.

### Métodos principales

- **addBook(Book book):**  
  Agrega un libro a la biblioteca.

- **getBook(int index):**  
  Obtiene el libro en la posición indicada.  
  Devuelve `null` si el índice es inválido.

- **deleteBook(int index):**  
  Elimina el libro en la posición indicada.  
  Devuelve `true` si el libro fue eliminado, `false` si el índice es inválido.

- **indexOf(String title):**  
  Busca el índice de un libro por su título (ignorando mayúsculas/minúsculas).  
  Devuelve el índice si se encuentra, `-1` si no existe.

- **getBooks():**  
  Devuelve la lista completa de libros en la biblioteca.

- **size():**  
  Devuelve la cantidad de libros almacenados.

### Ejemplo de uso

```java
Library library = new Library();
Book book1 = new Book(1, "Cien años de soledad", "Gabriel García Márquez");
library.addBook(book1);

int index = library.indexOf("Cien años de soledad"); // Devuelve 0
Book found = library.getBook(index); // Devuelve book1

boolean eliminado = library.deleteBook(index); // Devuelve true
int cantidad = library.size(); // Devuelve 0
```

### Detalles de implementación

- Los libros se almacenan en una lista interna (`List<Book>`).
- El acceso y manipulación de los libros se realiza mediante los métodos públicos de la clase.
- La búsqueda por título no distingue entre mayúsculas y minúsculas.


-------------------------------------------------------------------------------------------------

# Manual de uso - Proyecto Libreria

Este manual explica cómo utilizar todas las clases principales del proyecto Libreria para gestionar una colección de libros en Java.

---

## 1. Estructura de Clases

- **Book**  
  Representa un libro con atributos: id, título y autor.

- **Library**  
  Gestiona una colección de libros. Permite agregar, obtener, eliminar y buscar libros.

- **LibraryUI**  
  Proporciona una interfaz de usuario por consola para interactuar con la biblioteca.

- **Main**  
  Punto de entrada de la aplicación.

---

## 2. Uso de la clase Book

```java
Book libro = new Book(1, "El Principito", "Antoine de Saint-Exupéry");
System.out.println(libro.getTitle()); // "El Principito"
System.out.println(libro.getAuthor()); // "Antoine de Saint-Exupéry"
```

---

## 3. Uso de la clase Library

```java
Library library = new Library();
library.addBook(new Book(1, "1984", "George Orwell"));
library.addBook(new Book(2, "Cien años de soledad", "Gabriel García Márquez"));

// Obtener libro por índice
Book libro = library.getBook(0);

// Buscar índice por título
int indice = library.indexOf("1984");

// Eliminar libro por índice
boolean eliminado = library.deleteBook(indice);

// Listar todos los libros
for (Book b : library.getBooks()) {
    System.out.println(b.getTitle() + " - " + b.getAuthor());
}

// Cantidad de libros
int cantidad = library.size();
```

---

## 4. Uso de la clase LibraryUI

La clase `LibraryUI` permite interactuar con la biblioteca desde la consola. Normalmente se usa así:

```java
Library library = new Library();
LibraryUI ui = new LibraryUI(library);
ui.start(); // Inicia el menú interactivo por consola
```

Las opciones típicas incluyen:
- Añadir libro
- Buscar libro por título
- Eliminar libro por índice
- Mostrar todos los libros

---

## 5. Ejecución de la aplicación

Compila todos los archivos Java y ejecuta la clase principal:

```sh
javac Libreria/*.java
java Libreria.Main
```

---

## 6. Notas adicionales

- Los índices de los libros comienzan en 0.
- Si intentas acceder o eliminar un libro con un índice inválido, los métodos devuelven `null` o `false`.
- La búsqueda por título no distingue entre mayúsculas y minúsculas.
- Puedes modificar y ampliar las clases según tus necesidades.

---

## Porque usamos ArrayList en vez de LinkedList
Acceso rápido por índice:
La clase Library accede frecuentemente a los libros por su posición (índice), y ArrayList permite acceder a cualquier elemento en tiempo constante (O(1)), mientras que LinkedList requiere recorrer la lista (O(n)).

Bajas inserciones/eliminaciones en el medio:
En la biblioteca, la mayoría de las operaciones son agregar al final, buscar y eliminar por índice, que son más eficientes en ArrayList si no se realizan muchas inserciones/eliminaciones en posiciones intermedias.

Menor consumo de memoria:
ArrayList utiliza menos memoria por elemento, ya que no necesita almacenar referencias adicionales como LinkedList.

Simplicidad y rendimiento:
Para colecciones pequeñas o medianas, ArrayList suele ser más rápido y sencillo de usar.

Resumen:
Se usa ArrayList porque ofrece mejor rendimiento para las operaciones principales de la biblioteca (acceso por índice, agregados al final y búsquedas), y es más eficiente en memoria para este caso de uso.