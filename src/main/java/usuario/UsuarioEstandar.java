package usuario;

import operacionComercial.OperacionEgreso;
import organizacion.Organizacion;

public class UsuarioEstandar implements Rol{

    protected Organizacion organizacion;

    public UsuarioEstandar(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public OperacionEgreso registrarNuevaOperacionDeEgreso(){
        return new OperacionEgreso(null, 0, null, null, null, null, null, null);
    }


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
