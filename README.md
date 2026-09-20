# Taller 2 - Pensamiento computacional

## Abstracción

**¿Qué se solicita finalmente?**  
Desarrollar un sistema Java con interfaz JavaFX para administrar libros y préstamos de una biblioteca universitaria.

**¿Qué información es relevante?**  
De cada libro: código, título, autor, categoría y estado. Las categorías manejadas por el sistema son `INGENIERIA`, `CIENCIAS` y `LITERATURA`. De la biblioteca: nombre, dirección y porcentaje de multa. De los préstamos: libro, fecha de préstamo, fecha límite y fecha de devolución.

**¿Cómo se agrupa la información relevante?**  
Se agrupa en las clases del modelo `Libro`, `Prestamo`, `ConfiguracionBiblioteca`, `ServicioBiblioteca`, `CategoriaLibro` y `EstadoLibro`.

**¿Qué funcionalidades se solicitan?**  
Registrar libros, construir libros con los datos obligatorios y valores predeterminados cuando corresponde, clonar libros conservando sus características principales, realizar préstamos, registrar devoluciones y actualizar el estado del libro.

## Descomposición

**¿Cómo se distribuyen las funcionalidades?**

- `Libro`: almacena la información y cambia su estado.
- `Prestamo`: registra el libro prestado, las fechas del préstamo y controla si está activo o finalizado.
- `ConfiguracionBiblioteca`: guarda la información general de la biblioteca mediante una única instancia compartida.
- `ServicioBiblioteca`: contiene las reglas de negocio para registrar libros, evitar códigos duplicados, prestar, devolver y clonar libros.
- `BibliotecaController`: conecta la vista JavaFX con el servicio sin mezclar la lógica de negocio con la interfaz.
- Interfaz JavaFX: permite al usuario registrar libros, clonar libros, prestar, devolver y configurar la biblioteca.

**¿Qué debo hacer para probar las funcionalidades?**

1. Registrar un libro y comprobar que aparece disponible.
2. Intentar registrar otro libro con el mismo código y verificar que no se permita.
3. Prestar un libro disponible y comprobar que cambie a `PRESTADO`.
4. Intentar prestar el mismo libro otra vez y verificar que no se permita.
5. Devolver el libro y comprobar que cambie a `DISPONIBLE`.
6. Clonar un libro y comprobar que el nuevo libro tenga un código diferente.
7. Actualizar la configuración de la biblioteca y comprobar que se mantenga una única configuración general.

## Reconocimiento de patrones

**Singleton:**  
Se aplica en `ConfiguracionBiblioteca`, porque el nombre, la dirección y el porcentaje de multa deben ser consistentes en todo el sistema. Por eso se utiliza una única instancia accesible mediante `getInstancia()`.

**Builder:**  
Se aplica en `Libro`, porque permite crear libros de forma clara y controlada. El código, el título y el autor son datos obligatorios, mientras que la categoría puede definirse mediante el builder o tomar un valor predeterminado.

**Prototype:**  
Se aplica en `Libro` mediante el método `clonar(String nuevoCodigo)`. Esto permite crear un nuevo libro a partir de otro existente, conservando sus características principales y cambiando únicamente el código.

**Principios SOLID aplicados:**  
`Libro` se encarga de sus datos y estado, `Prestamo` maneja la información del préstamo, `ConfiguracionBiblioteca` administra la configuración general, `ServicioBiblioteca` concentra las reglas de negocio y `BibliotecaController` coordina la interfaz. Esta separación respeta la responsabilidad única y permite mantener el código organizado, extensible y coherente con MVC.

## Codificación

**¿Cómo pruebo la solución en Java?**  
Con pruebas unitarias para registrar libros, impedir códigos duplicados, prestar, devolver, clonar libros y validar la configuración única de la biblioteca; también ejecutando la interfaz JavaFX y verificando cada caso de prueba.

**¿Cómo escribo la solución en Java?**  
Se crean las clases del modelo, un servicio con las reglas de negocio, un controlador para comunicar la vista con el modelo y una interfaz JavaFX organizada con el patrón MVC. El modelo contiene la lógica principal, la vista se define en FXML y el controlador atiende las acciones del usuario.
