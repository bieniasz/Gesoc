package usuario;

import organizacion.EntidadJuridica;
import organizacion.Organizacion;

public class UsuarioAdmin implements Rol{

    public UsuarioAdmin() {

    }

    @Override
    public Usuario nuevoUsuarioEstandar(String usuario, String contrasenia, Organizacion organizacion){
        Usuario usuarioNuevo = new Usuario(usuario, contrasenia);
        usuarioNuevo.setRol(new UsuarioEstandar(organizacion));
        return usuarioNuevo;
    }

    @Override
    public Organizacion getOrganizacion() {
        return null;
    }

    public void altaProveedor(){

    }

    public void altaTipoComprobante(){

    }

    @Override
    public Organizacion altaOrganizacionJuridica(){
    return new EntidadJuridica();
    }

}
