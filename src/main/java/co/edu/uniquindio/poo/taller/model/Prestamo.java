package co.edu.uniquindio.poo.taller.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Prestamo {
    private final Libro libro;
    private final LocalDate fechaPrestamo;
    private final LocalDate fechaLimite;
    private LocalDate fechaDevolucion;

    /** Crea un prestamo validando el libro y el rango de fechas. */
    public Prestamo(Libro libro, LocalDate fechaPrestamo, LocalDate fechaLimite) {
        this.libro = Objects.requireNonNull(libro, "El libro es obligatorio");
        this.fechaPrestamo = Objects.requireNonNull(fechaPrestamo, "La fecha de prestamo es obligatoria");
        this.fechaLimite = Objects.requireNonNull(fechaLimite, "La fecha limite es obligatoria");
        if (fechaLimite.isBefore(fechaPrestamo)) {
            throw new IllegalArgumentException("La fecha limite no puede ser anterior al prestamo");
        }
    }

    /** Crea un prestamo desde la fecha actual con una duracion en dias. */
    public static Prestamo crear(Libro libro, int diasPrestamo) {
        if (diasPrestamo <= 0) {
            throw new IllegalArgumentException("Los dias de prestamo deben ser mayores que cero");
        }
        LocalDate hoy = LocalDate.now();
        return new Prestamo(libro, hoy, hoy.plusDays(diasPrestamo));
    }

    /** Registra la devolucion y cierra un prestamo que todavia esta activo. */
    public void finalizar(LocalDate fechaDevolucion) {
        if (!estaActivo()) {
            throw new IllegalStateException("El prestamo ya fue finalizado");
        }
        this.fechaDevolucion = Objects.requireNonNull(fechaDevolucion, "La fecha de devolucion es obligatoria");
    }

    /** Indica si el prestamo no ha registrado una fecha de devolucion. */
    public boolean estaActivo() {
        return fechaDevolucion == null;
    }

    /** Calcula los dias de retraso con respecto a la fecha limite. */
    public long getDiasRetraso() {
        LocalDate fechaComparacion = fechaDevolucion == null ? LocalDate.now() : fechaDevolucion;
        return Math.max(0, ChronoUnit.DAYS.between(fechaLimite, fechaComparacion));
    }

    /** Calcula la multa usando el porcentaje configurado para la biblioteca. */
    public double calcularMulta() {
        return getDiasRetraso() * ConfiguracionBiblioteca.getInstancia().getPorcentajeMulta();
    }

    /** Retorna el libro asociado a este prestamo. */
    public Libro getLibro() {
        return libro;
    }

    /** Retorna la fecha en que se creo el prestamo. */
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    /** Retorna la fecha maxima acordada para devolver el libro. */
    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    /** Retorna la fecha de devolucion o null si el prestamo sigue activo. */
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    /** Devuelve un texto legible que identifica el estado del prestamo. */
    public String getEstado() {
        return estaActivo() ? "ACTIVO" : "FINALIZADO";
    }
}
