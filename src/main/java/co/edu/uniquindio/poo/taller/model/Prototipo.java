package co.edu.uniquindio.poo.taller.model;

public interface Prototipo<T> {
    /** Crea una copia del objeto con el identificador solicitado. */
    T clonar(String nuevoCodigo);
}
