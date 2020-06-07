package usuario;

import operacionComercial.OperacionEgreso;
import organizacion.Organizacion;

public class UsuarioRevisor implements Rol{
    private Organizacion organizacion;

    public UsuarioRevisor(Organizacion organizacion){
        this.organizacion = organizacion;
    }

    public void crearRevisionDeCompra(OperacionEgreso operacionEgreso){

    }

    @Override
    public Organizacion altaOrganizacionJuridica() {
        return null;
    }

    @Override
    public Usuario nuevoUsuarioEstandar(String usuario, String contrasenia, Organizacion organizacion) {
        return null;
    }

    @Override
    public Organizacion getOrganizacion() {
        return null;
    }
}
