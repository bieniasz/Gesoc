package usuario;

import organizacion.Organizacion;

public interface Rol {

    Organizacion altaOrganizacionJuridica();
    
    Usuario nuevoUsuarioEstandar(String usuario, String contrasenia, Organizacion organizacion);

    Organizacion getOrganizacion();
}
