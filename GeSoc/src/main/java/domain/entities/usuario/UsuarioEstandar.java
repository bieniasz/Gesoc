package domain.entities.usuario;

import domain.entities.organizacion.Organizacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("Estandar")
public class UsuarioEstandar extends Rol{

    @ManyToOne
    @JoinColumn(name= "domain/entities/organizacion",referencedColumnName = "id")
    protected Organizacion organizacion;

    public UsuarioEstandar(Organizacion organizacion) {
        this.organizacion = organizacion;
    }


    @Override
    public Organizacion getOrganizacion() {
        return this.organizacion;
    }

    @Override
    public Organizacion altaOrganizacionJuridica() {
        return null;
    }

    @Override
    public Usuario nuevoUsuarioEstandar(String usuario, String contrasenia, Organizacion organizacion) {
        return null;
    }
}
