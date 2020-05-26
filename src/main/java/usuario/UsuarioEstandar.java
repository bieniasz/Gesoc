package usuario;

import operacionComercial.OperacionEgreso;
import organizacion.Organizacion;

public class UsuarioEstandar extends Usuario{

    protected Organizacion organizacion;
    // private BandejaDeMensajes bandejaDeMensajes;

    protected UsuarioEstandar(String usuario, String contrasenia) {

        this.usuario = usuario;
        this.contrasenia = contrasenia;

    }

    public OperacionEgreso registrarNuevaOperacionDeEgreso(){
    return new OperacionEgreso();
    }

    public void validarEgresosYPresupuestos(){

    }

    public void bandejaDeMensajes(){

    }

    public void suscribirseACompra(){

    }

    public Organizacion getOrganizacion() {
        return this.organizacion;
    }
}
