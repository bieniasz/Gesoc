package domain.entities.usuario;

import domain.entities.organizacion.EntidadJuridica;
import domain.entities.organizacion.Organizacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Administrador")
public class UsuarioAdmin extends Rol{

    public UsuarioAdmin() {
    }

    //TODO: crear metodo para crear usuarios? los vamos a estar insertando directo en la db
    //TODO: reveer toda esta herencia
    @Override
    public Usuario nuevoUsuarioEstandar(String usuario, String contrasenia, Organizacion organizacion) throws Exception {
        /*ValidadorDeUsuario validadorDeUsuario = new ValidadorDeUsuario();
        Usuario usuarioNuevo = validadorDeUsuario.crearUsuario(domain.entities.usuario,contrasenia);
        usuarioNuevo.setRol(new UsuarioEstandar(domain.entities.organizacion));
        return usuarioNuevo;*/
        return new Usuario();
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