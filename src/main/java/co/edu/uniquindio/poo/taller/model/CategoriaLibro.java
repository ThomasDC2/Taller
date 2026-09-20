package co.edu.uniquindio.poo.taller.model;

public enum CategoriaLibro {
    INGENIERIA("Ingenieria"),
    CIENCIAS("Ciencias"),
    LITERATURA("Literatura");

    private final String nombre;

    CategoriaLibro(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
