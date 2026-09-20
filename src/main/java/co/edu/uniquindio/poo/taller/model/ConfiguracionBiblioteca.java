package co.edu.uniquindio.poo.taller.model;

public final class ConfiguracionBiblioteca {
    private static volatile ConfiguracionBiblioteca instancia;

    private String nombre;
    private String direccion;
    private double porcentajeMulta;

    private ConfiguracionBiblioteca() {
        this.nombre = "Biblioteca universitaria";
        this.direccion = "Campus principal";
        this.porcentajeMulta = 5.0;
    }

    public static ConfiguracionBiblioteca getInstancia() {
        if (instancia == null) {
            synchronized (ConfiguracionBiblioteca.class) {
                if (instancia == null) {
                    instancia = new ConfiguracionBiblioteca();
                }
            }
        }
        return instancia;
    }

    public void actualizar(String nombre, String direccion, double porcentajeMulta) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la biblioteca es obligatorio");
        }
        if (direccion == null || direccion.isBlank()) {
            throw new IllegalArgumentException("La direccion es obligatoria");
        }
        if (porcentajeMulta < 0) {
            throw new IllegalArgumentException("El porcentaje de multa no puede ser negativo");
        }
        this.nombre = nombre.trim();
        this.direccion = direccion.trim();
        this.porcentajeMulta = porcentajeMulta;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getPorcentajeMulta() {
        return porcentajeMulta;
    }
}
