package usuario;

import organizacion.EntidadJuridica;
import organizacion.Organizacion;

public class UsuarioAdmin extends Usuario{

    public UsuarioAdmin(String usuario, String contrasenia) {

        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public UsuarioEstandar nuevoUsuarioEstandar(String usuario, String contrasenia){
        return new UsuarioEstandar(usuario, contrasenia);
    }

    public void altaProveedor(){

    }

    public void altaTipoComprobante(){

    }

    public Organizacion altaOrganizacionJuridica(){
    return new EntidadJuridica();
    }

    public void asignarOrganizacionAUsuarioEstandar(UsuarioEstandar usuario, Organizacion organizacion){
        usuario.organizacion = organizacion;
    }

}
