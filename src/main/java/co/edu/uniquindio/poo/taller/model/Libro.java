package co.edu.uniquindio.poo.taller.model;

import java.util.Objects;

public class Libro implements Prototipo<Libro> {
    private final String codigo;
    private final String titulo;
    private final String autor;
    private final CategoriaLibro categoria;
    private EstadoLibro estado;

    /** Construye un libro a partir de los datos reunidos por el Builder. */
    private Libro(Builder builder) {
        this.codigo = validarTexto(builder.codigo, "El codigo es obligatorio");
        this.titulo = validarTexto(builder.titulo, "El titulo es obligatorio");
        this.autor = validarTexto(builder.autor, "El autor es obligatorio");
        this.categoria = Objects.requireNonNullElse(builder.categoria, CategoriaLibro.INGENIERIA);
        this.estado = Objects.requireNonNullElse(builder.estado, EstadoLibro.DISPONIBLE);
    }

    /** Inicia la construccion de un libro con sus datos obligatorios. */
    public static Builder builder(String codigo, String titulo, String autor) {
        return new Builder(codigo, titulo, autor);
    }

    /** Valida y normaliza un texto obligatorio antes de asignarlo al libro. */
    private static String validarTexto(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensaje);
        }
        return valor.trim();
    }

    @Override
    /** Crea una copia disponible del libro usando un codigo nuevo. */
    public Libro clonar(String nuevoCodigo) {
        return Libro.builder(nuevoCodigo, titulo, autor)
                .categoria(categoria)
                .estado(EstadoLibro.DISPONIBLE)
                .build();
    }

    /** Cambia el estado a PRESTADO cuando el libro esta disponible. */
    public void prestar() {
        if (estado != EstadoLibro.DISPONIBLE) {
            throw new IllegalStateException("El libro no esta disponible para prestamo");
        }
        estado = EstadoLibro.PRESTADO;
    }

    /** Cambia el estado a DISPONIBLE cuando el libro estaba prestado. */
    public void devolver() {
        if (estado != EstadoLibro.PRESTADO) {
            throw new IllegalStateException("El libro no se encuentra prestado");
        }
        estado = EstadoLibro.DISPONIBLE;
    }

    /** Retorna el codigo unico del libro. */
    public String getCodigo() {
        return codigo;
    }

    /** Retorna el titulo del libro. */
    public String getTitulo() {
        return titulo;
    }

    /** Retorna el autor del libro. */
    public String getAutor() {
        return autor;
    }

    /** Retorna la categoria asignada al libro. */
    public CategoriaLibro getCategoria() {
        return categoria;
    }

    /** Retorna el estado actual de disponibilidad del libro. */
    public EstadoLibro getEstado() {
        return estado;
    }

    @Override
    /** Genera una representacion corta para listas y tablas de la interfaz. */
    public String toString() {
        return codigo + " - " + titulo;
    }

    public static class Builder {
        private final String codigo;
        private final String titulo;
        private final String autor;
        private CategoriaLibro categoria;
        private EstadoLibro estado;

        /** Recibe los tres datos obligatorios para crear un libro. */
        private Builder(String codigo, String titulo, String autor) {
            this.codigo = codigo;
            this.titulo = titulo;
            this.autor = autor;
        }

        /** Asigna opcionalmente la categoria del libro. */
        public Builder categoria(CategoriaLibro categoria) {
            this.categoria = categoria;
            return this;
        }

        /** Asigna un estado inicial; por defecto el libro queda disponible. */
        public Builder estado(EstadoLibro estado) {
            this.estado = estado;
            return this;
        }

        /** Crea el objeto Libro con los valores recopilados. */
        public Libro build() {
            return new Libro(this);
        }
    }
}
