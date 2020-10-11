package usuario;

import operacionComercial.EntidadPersistente;

import javax.persistence.*;

/**
 * TODO que una clase tan importante como usuario sea anemic.
 * Revisar esto. Ninguna clase a esta altura deberia ser anemica, esto solo seria
 * aceptado si es un DTO o una clase usada para contrato de interfaz.
 */

@Entity
@Table
public class Usuario extends EntidadPersistente {

    @Column
    private String usuarioId;

    //TODO cifrar contrasenia
    @Column
    private String contrasenias;

    @ManyToOne
    private Rol rol;

    public String getUsuario() {
        return this.usuarioId;
    }
    public String getContrasenia() {return this.contrasenias; }
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
    public void setContrasenia(String contrasenia) { this.contrasenias = contrasenia; }
}