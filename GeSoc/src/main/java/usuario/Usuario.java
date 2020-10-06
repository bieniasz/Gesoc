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
    protected String usuario;

    @ManyToOne
    @JoinColumn(name="rol",referencedColumnName = "id")
    protected Rol rol;

    @Column
    private boolean activo;

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

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
