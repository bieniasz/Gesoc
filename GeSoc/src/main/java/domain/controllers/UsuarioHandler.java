package domain.controllers;

import domain.entities.usuario.Usuario;

import java.util.Map;

public class UsuarioHandler {

    public void agregarDatosDeUsuario(Map<String, Object> parametros, Usuario usuario){
        String nombreFicticioOrganizacion = usuario.getRol().getOrganizacion().getNombreFicticio();

        String rol = usuario.getRol().getClass().getName();


        switch (rol){
            case "domain.entities.usuario.UsuarioEstandar":
                parametros.put("usuarioEstandar", "Usuario Estándar");
                break;
            case "domain.entities.usuario.UsuarioRevisor":
                parametros.put("usuarioRevisor", "Usuario Revisor");
                break;
            case "domain.entities.usuario.UsuarioAdmin":
                parametros.put("usuarioAdmin", "Usuario Administrador");
                break;
        }

        parametros.put("usuarioId", usuario.getUsuarioId());
        parametros.put("nombreFicticioOrganizacion", nombreFicticioOrganizacion);

    }
}
