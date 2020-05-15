package seguridad;

/**
 * TODO que una clase tan importante como usuario sea anemic.
 * Revisar esto. Ninguna clase a esta altura deberia ser anemica, esto solo seria
 * aceptado si es un DTO o una clase usada para contrato de interfaz.
 */

public class Usuario {

    private String nombre;
    private String contrasenia;
    private String tipo;

    public Usuario(String nombre, String contrasenia) {

        this.nombre = nombre;
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getTipo() {
        return tipo;
    }



}
