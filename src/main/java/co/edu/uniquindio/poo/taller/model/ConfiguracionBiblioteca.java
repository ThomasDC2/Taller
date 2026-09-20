package co.edu.uniquindio.poo.taller.model;

public final class ConfiguracionBiblioteca {
    private static volatile ConfiguracionBiblioteca instancia;

    private String nombre;
    private String direccion;
    private double porcentajeMulta;

    /** Evita instancias externas y define los valores iniciales de configuracion. */
    private ConfiguracionBiblioteca() {
        this.nombre = "Biblioteca universitaria";
        this.direccion = "Campus principal";
        this.porcentajeMulta = 5.0;
    }

    /** Obtiene la unica instancia compartida de configuracion de la biblioteca. */
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

    /** Actualiza los datos globales despues de validar sus valores. */
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

    /** Retorna el nombre configurado para la biblioteca. */
    public String getNombre() {
        return nombre;
    }

    /** Retorna la direccion configurada para la biblioteca. */
    public String getDireccion() {
        return direccion;
    }

    /** Retorna el valor de multa aplicado por cada dia de retraso. */
    public double getPorcentajeMulta() {
        return porcentajeMulta;
    }
}
