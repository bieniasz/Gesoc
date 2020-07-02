package usuario;

import seguridad.ValidadorDeUsuario;

import java.util.List;

/**
 * TODO que una clase tan importante como usuario sea anemic.
 * Revisar esto. Ninguna clase a esta altura deberia ser anemica, esto solo seria
 * aceptado si es un DTO o una clase usada para contrato de interfaz.
 */

public class Usuario {

    protected String usuario;
    protected Rol rol;

    public Usuario(String usuario) {
      this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
