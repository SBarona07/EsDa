# Proyecto Librería - EsDa

## Descripción

Este proyecto implementa una aplicación de consola en Java para gestionar una colección de libros, permitiendo agregar, buscar, eliminar y mostrar libros. Está diseñado como ejemplo de uso de estructuras de datos y buenas prácticas de programación orientada a objetos.

---

## Decisiones técnicas

- **Lenguaje:** Java, por su robustez y facilidad para manejar estructuras de datos.
- **Estructura de datos:**  
  Se utiliza `ArrayList` para almacenar los libros, ya que permite acceso rápido por índice y es eficiente para agregar elementos al final.  
  La colección se declara como `List<Book>` para facilitar un posible cambio de implementación en el futuro.
- **Separación de responsabilidades:**  
  - `Book`: Representa un libro con id, título y autor.
  - `Library`: Gestiona la colección de libros y provee métodos para manipularla.
  - `LibraryUI`: Proporciona una interfaz de usuario por consola para interactuar con la biblioteca.
  - `Main`: Clase principal que inicia la aplicación.
- **Interfaz de usuario:**  
  Se utiliza la consola para la interacción, empleando la clase `Scanner` para leer la entrada del usuario.
- **Validación:**  
  Los métodos verifican la validez de los índices y entradas para evitar errores y mejorar la experiencia de usuario.
- **Comentarios:**  
  El código está ampliamente comentado para facilitar su comprensión y mantenimiento.

---
## ¿Por qué usamos ArrayList en vez de LinkedList?

Se eligió `ArrayList` sobre `LinkedList` porque en esta aplicación las operaciones más frecuentes son el acceso por índice y la iteración sobre la colección de libros, tareas en las que `ArrayList` es más eficiente gracias a su almacenamiento contiguo en memoria.  
`LinkedList` sería más adecuado si se realizaran muchas inserciones o eliminaciones en posiciones arbitrarias, pero en nuestro caso, la mayoría de las operaciones son agregar al final y acceder por índice, donde `ArrayList` ofrece mejor rendimiento y menor consumo de memoria.


## Estructura del código

- **Libreria/Book.java**  
  Define la clase `Book` con atributos `id`, `title` y `author`, y métodos para acceder a ellos.

- **Libreria/Library.java**  
  Implementa la clase `Library`, que gestiona la colección de libros mediante un `ArrayList`.  
  Métodos principales:  
  - `addBook(Book book)`
  - `getBook(int index)`
  - `deleteBook(int index)`
  - `indexOf(String title)`
  - `getBooks()`
  - `size()`

- **Libreria/LibraryUI.java**  
  Proporciona la interfaz de usuario por consola. Permite al usuario:  
  - Añadir libros
  - Buscar libros por título
  - Eliminar libros por índice
  - Mostrar todos los libros
  - Buscar el índice de un libro por título

- **Libreria/Main.java**  
  Clase principal que crea la biblioteca y la interfaz de usuario, y lanza el menú interactivo.

---

## Ejecución

1. Compila los archivos Java:
   ```sh
   javac Libreria/*.java
   ```
2. Ejecuta la aplicación:
   ```sh
   java Libreria.Main
   ```

---

## Notas

- Los índices de los libros comienzan en 0.
- La búsqueda por título no distingue mayúsculas/minúsculas.
- El código está preparado para ser fácilmente ampliado o modificado.

---