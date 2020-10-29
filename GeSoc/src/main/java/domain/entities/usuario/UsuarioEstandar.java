package domain.entities.usuario;

import domain.entities.organizacion.Organizacion;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Estandar")
public class UsuarioEstandar extends Rol{

    public UsuarioEstandar() {
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "organizacion",referencedColumnName = "id")
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
