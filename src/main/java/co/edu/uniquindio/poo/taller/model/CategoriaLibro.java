package co.edu.uniquindio.poo.taller.model;

public enum CategoriaLibro {
    INGENIERIA("Ingenieria"),
    CIENCIAS("Ciencias"),
    LITERATURA("Literatura");

    private final String nombre;

    /** Asigna el nombre visible que se muestra para cada categoria. */
    CategoriaLibro(String nombre) {
        this.nombre = nombre;
    }

    @Override
    /** Devuelve el nombre legible de la categoria para la interfaz. */
    public String toString() {
        return nombre;
    }
}
