package domain.entities.usuario;

import domain.entities.operacionComercial.EntidadPersistente;
import domain.entities.organizacion.Organizacion;

import javax.persistence.*;

@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TipoRol")
public abstract class Rol extends EntidadPersistente { //La hicimos clase abstracta para poder persistir

    public Rol() {
    }

    @Column
    private boolean activo;

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    abstract public Organizacion altaOrganizacionJuridica();

    abstract public Usuario nuevoUsuarioEstandar(String usuario, String contrasenia, Organizacion organizacion) throws Exception;

    abstract public Organizacion getOrganizacion();

    public boolean isActivo() {
        return activo;
    }

}
