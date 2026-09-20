package co.edu.uniquindio.poo.taller.model;

import java.util.Objects;

public class Libro implements Prototipo<Libro> {
    private final String codigo;
    private final String titulo;
    private final String autor;
    private final CategoriaLibro categoria;
    private EstadoLibro estado;

    private Libro(Builder builder) {
        this.codigo = validarTexto(builder.codigo, "El codigo es obligatorio");
        this.titulo = validarTexto(builder.titulo, "El titulo es obligatorio");
        this.autor = validarTexto(builder.autor, "El autor es obligatorio");
        this.categoria = Objects.requireNonNullElse(builder.categoria, CategoriaLibro.INGENIERIA);
        this.estado = Objects.requireNonNullElse(builder.estado, EstadoLibro.DISPONIBLE);
    }

    public static Builder builder(String codigo, String titulo, String autor) {
        return new Builder(codigo, titulo, autor);
    }

    private static String validarTexto(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensaje);
        }
        return valor.trim();
    }

    @Override
    public Libro clonar(String nuevoCodigo) {
        return Libro.builder(nuevoCodigo, titulo, autor)
                .categoria(categoria)
                .estado(EstadoLibro.DISPONIBLE)
                .build();
    }

    public void prestar() {
        if (estado != EstadoLibro.DISPONIBLE) {
            throw new IllegalStateException("El libro no esta disponible para prestamo");
        }
        estado = EstadoLibro.PRESTADO;
    }

    public void devolver() {
        if (estado != EstadoLibro.PRESTADO) {
            throw new IllegalStateException("El libro no se encuentra prestado");
        }
        estado = EstadoLibro.DISPONIBLE;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public CategoriaLibro getCategoria() {
        return categoria;
    }

    public EstadoLibro getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return codigo + " - " + titulo;
    }

    public static class Builder {
        private final String codigo;
        private final String titulo;
        private final String autor;
        private CategoriaLibro categoria;
        private EstadoLibro estado;

        private Builder(String codigo, String titulo, String autor) {
            this.codigo = codigo;
            this.titulo = titulo;
            this.autor = autor;
        }

        public Builder categoria(CategoriaLibro categoria) {
            this.categoria = categoria;
            return this;
        }

        public Builder estado(EstadoLibro estado) {
            this.estado = estado;
            return this;
        }

        public Libro build() {
            return new Libro(this);
        }
    }
}
