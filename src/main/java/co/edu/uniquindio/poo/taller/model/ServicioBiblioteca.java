package co.edu.uniquindio.poo.taller.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ServicioBiblioteca {
    private final List<Libro> libros = new ArrayList<>();
    private final List<Prestamo> prestamos = new ArrayList<>();

    public void registrarLibro(Libro libro) {
        if (libro == null) {
            throw new IllegalArgumentException("El libro es obligatorio");
        }
        if (buscarLibroPorCodigo(libro.getCodigo()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un libro con el codigo " + libro.getCodigo());
        }
        libros.add(libro);
    }

    public Libro clonarLibro(String codigoBase, String nuevoCodigo) {
        Libro libroBase = buscarLibroPorCodigo(codigoBase)
                .orElseThrow(() -> new IllegalArgumentException("No existe el libro base"));
        Libro copia = libroBase.clonar(nuevoCodigo);
        registrarLibro(copia);
        return copia;
    }

    public Prestamo prestarLibro(String codigoLibro, int diasPrestamo) {
        Libro libro = buscarLibroPorCodigo(codigoLibro)
                .orElseThrow(() -> new IllegalArgumentException("No existe el libro indicado"));
        libro.prestar();
        Prestamo prestamo = Prestamo.crear(libro, diasPrestamo);
        prestamos.add(prestamo);
        return prestamo;
    }

    public Prestamo devolverLibro(String codigoLibro) {
        Libro libro = buscarLibroPorCodigo(codigoLibro)
                .orElseThrow(() -> new IllegalArgumentException("No existe el libro indicado"));
        Prestamo prestamo = buscarPrestamoActivo(codigoLibro)
                .orElseThrow(() -> new IllegalStateException("El libro no tiene un prestamo activo"));
        libro.devolver();
        prestamo.finalizar(LocalDate.now());
        return prestamo;
    }

    public Optional<Libro> buscarLibroPorCodigo(String codigo) {
        if (codigo == null) {
            return Optional.empty();
        }
        return libros.stream()
                .filter(libro -> libro.getCodigo().equalsIgnoreCase(codigo.trim()))
                .findFirst();
    }

    public Optional<Prestamo> buscarPrestamoActivo(String codigoLibro) {
        if (codigoLibro == null) {
            return Optional.empty();
        }
        return prestamos.stream()
                .filter(Prestamo::estaActivo)
                .filter(prestamo -> prestamo.getLibro().getCodigo().equalsIgnoreCase(codigoLibro.trim()))
                .findFirst();
    }

    public List<Libro> obtenerLibros() {
        return Collections.unmodifiableList(libros);
    }

    public List<Prestamo> obtenerPrestamos() {
        return Collections.unmodifiableList(prestamos);
    }
}
