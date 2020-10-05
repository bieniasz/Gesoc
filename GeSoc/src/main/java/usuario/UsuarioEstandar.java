package usuario;

import operacionComercial.OperacionEgreso;
import organizacion.Organizacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("Estandar")
public class UsuarioEstandar extends Rol{

    @ManyToOne
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
