# Taller 2 - Pensamiento computacional

## Abstracción

**¿Qué se solicita finalmente?**  
Desarrollar un sistema Java con interfaz JavaFX para administrar libros y préstamos de una biblioteca universitaria.

**¿Qué información es relevante?**  
De cada libro: código, título, autor, categoría y estado. De la biblioteca: nombre, dirección y porcentaje de multa. De los préstamos: libro, fecha de préstamo, fecha límite y devolución.

**¿Cómo se agrupa la información relevante?**  
Se agrupa en las clases `Libro`, `Prestamo` y `ConfiguracionBiblioteca`.

**¿Qué funcionalidades se solicitan?**  
Registrar libros, crear libros con datos opcionales, clonar libros, prestar, devolver y actualizar el estado del libro.

## Descomposición

**¿Cómo se distribuyen las funcionalidades?**

- `Libro`: almacena la información y cambia su estado.
- `Prestamo`: registra las fechas y controla si está activo o finalizado.
- `ConfiguracionBiblioteca`: guarda la información general de la biblioteca.
- `ServicioBiblioteca`: registra libros, realiza préstamos y devoluciones.
- Interfaz JavaFX: permite al usuario ejecutar las operaciones.

**¿Qué debo hacer para probar las funcionalidades?**

1. Registrar un libro y comprobar que aparece disponible.
2. Intentar registrar otro libro con el mismo código y verificar que no se permita.
3. Prestar un libro disponible y comprobar que cambie a `PRESTADO`.
4. Intentar prestar el mismo libro otra vez y verificar que no se permita.
5. Devolver el libro y comprobar que cambie a `DISPONIBLE`.
6. Clonar un libro y comprobar que el nuevo libro tenga un código diferente.

## Reconocimiento de patrones
N/A
## Codificación

**¿Cómo pruebo la solución en Java?**  
Con pruebas unitarias para registrar, prestar y devolver libros; también ejecutando la interfaz JavaFX y verificando cada caso de prueba.

**¿Cómo escribo la solución en Java?**  
Se crean las clases del modelo, un servicio con las reglas de negocio y la interfaz JavaFX organizada con el patrón MVC.
