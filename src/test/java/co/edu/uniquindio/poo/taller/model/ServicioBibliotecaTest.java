package co.edu.uniquindio.poo.taller.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ServicioBibliotecaTest {

    @Test
    void noDebeRegistrarLibrosConCodigoDuplicado() {
        ServicioBiblioteca servicio = new ServicioBiblioteca();
        Libro libro = Libro.builder("LIB-001", "POO", "Autor").build();

        servicio.registrarLibro(libro);

        assertThrows(IllegalArgumentException.class, () -> servicio.registrarLibro(libro));
    }

    @Test
    void debePrestarYDevolverUnLibroActualizandoEstado() {
        ServicioBiblioteca servicio = new ServicioBiblioteca();
        Libro libro = Libro.builder("LIB-002", "Patrones", "Gamma").build();
        servicio.registrarLibro(libro);

        servicio.prestarLibro("LIB-002", 7);

        assertEquals(EstadoLibro.PRESTADO, libro.getEstado());

        servicio.devolverLibro("LIB-002");

        assertEquals(EstadoLibro.DISPONIBLE, libro.getEstado());
    }

    @Test
    void noDebePrestarDosVecesElMismoLibro() {
        ServicioBiblioteca servicio = new ServicioBiblioteca();
        servicio.registrarLibro(Libro.builder("LIB-003", "Clean Code", "Martin").build());

        servicio.prestarLibro("LIB-003", 7);

        assertThrows(IllegalStateException.class, () -> servicio.prestarLibro("LIB-003", 7));
    }

    @Test
    void debeClonarLibroConPrototypeYCodigoNuevo() {
        ServicioBiblioteca servicio = new ServicioBiblioteca();
        Libro original = Libro.builder("LIB-004", "Arquitectura", "Docente")
                .categoria(CategoriaLibro.INGENIERIA)
                .build();
        servicio.registrarLibro(original);

        Libro copia = servicio.clonarLibro("LIB-004", "LIB-005");

        assertNotSame(original, copia);
        assertEquals("LIB-005", copia.getCodigo());
        assertEquals(original.getTitulo(), copia.getTitulo());
        assertEquals(original.getCategoria(), copia.getCategoria());
        assertEquals(EstadoLibro.DISPONIBLE, copia.getEstado());
    }

    @Test
    void configuracionBibliotecaDebeSerSingleton() {
        ConfiguracionBiblioteca primeraInstancia = ConfiguracionBiblioteca.getInstancia();
        ConfiguracionBiblioteca segundaInstancia = ConfiguracionBiblioteca.getInstancia();

        assertSame(primeraInstancia, segundaInstancia);
    }
}
