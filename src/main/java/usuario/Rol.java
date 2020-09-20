package usuario;

import organizacion.Organizacion;

public abstract class Rol { //La hicimos clase abstracta para poder persistir

    abstract public Organizacion altaOrganizacionJuridica();
    
    abstract public Usuario nuevoUsuarioEstandar(String usuario, String contrasenia, Organizacion organizacion) throws Exception;

    abstract public Organizacion getOrganizacion();
}
