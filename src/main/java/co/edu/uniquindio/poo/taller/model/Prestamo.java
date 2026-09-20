package co.edu.uniquindio.poo.taller.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Prestamo {
    private final Libro libro;
    private final LocalDate fechaPrestamo;
    private final LocalDate fechaLimite;
    private LocalDate fechaDevolucion;

    public Prestamo(Libro libro, LocalDate fechaPrestamo, LocalDate fechaLimite) {
        this.libro = Objects.requireNonNull(libro, "El libro es obligatorio");
        this.fechaPrestamo = Objects.requireNonNull(fechaPrestamo, "La fecha de prestamo es obligatoria");
        this.fechaLimite = Objects.requireNonNull(fechaLimite, "La fecha limite es obligatoria");
        if (fechaLimite.isBefore(fechaPrestamo)) {
            throw new IllegalArgumentException("La fecha limite no puede ser anterior al prestamo");
        }
    }

    public static Prestamo crear(Libro libro, int diasPrestamo) {
        if (diasPrestamo <= 0) {
            throw new IllegalArgumentException("Los dias de prestamo deben ser mayores que cero");
        }
        LocalDate hoy = LocalDate.now();
        return new Prestamo(libro, hoy, hoy.plusDays(diasPrestamo));
    }

    public void finalizar(LocalDate fechaDevolucion) {
        if (!estaActivo()) {
            throw new IllegalStateException("El prestamo ya fue finalizado");
        }
        this.fechaDevolucion = Objects.requireNonNull(fechaDevolucion, "La fecha de devolucion es obligatoria");
    }

    public boolean estaActivo() {
        return fechaDevolucion == null;
    }

    public long getDiasRetraso() {
        LocalDate fechaComparacion = fechaDevolucion == null ? LocalDate.now() : fechaDevolucion;
        return Math.max(0, ChronoUnit.DAYS.between(fechaLimite, fechaComparacion));
    }

    public double calcularMulta() {
        return getDiasRetraso() * ConfiguracionBiblioteca.getInstancia().getPorcentajeMulta();
    }

    public Libro getLibro() {
        return libro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public String getEstado() {
        return estaActivo() ? "ACTIVO" : "FINALIZADO";
    }
}
