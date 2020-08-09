package usuario;

import organizacion.EntidadJuridica;
import organizacion.Organizacion;
import seguridad.ValidadorDeUsuario;

public class UsuarioAdmin implements Rol{

    public UsuarioAdmin() {

    }

    @Override
    public Usuario nuevoUsuarioEstandar(String usuario, String contrasenia, Organizacion organizacion) throws Exception {
        ValidadorDeUsuario validadorDeUsuario = new ValidadorDeUsuario();
        Usuario usuarioNuevo = validadorDeUsuario.crearUsuario(usuario,contrasenia);
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
