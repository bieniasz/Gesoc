package usuario;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import organizacion.EntidadJuridica;
import organizacion.Organizacion;
import usuario.Usuario;
import usuario.UsuarioAdmin;

public class TestUsuarioAdmin {

    private Usuario usuarioAdmin = new Usuario("admin", "admin123");

    @Before
    public void init(){
        usuarioAdmin.setRol(new UsuarioAdmin());
    }

    @Test
    public void creacionDeOrganizacionJuridica(){
        Organizacion entidadJuridica = usuarioAdmin.getRol().altaOrganizacionJuridica();

        Assert.assertTrue(entidadJuridica instanceof EntidadJuridica);
    }

    @Test
    public void creacionDeUsuarioEstandar(){
        Organizacion entidadJuridica = usuarioAdmin.getRol().altaOrganizacionJuridica();
        Usuario usuarioEstandar = usuarioAdmin.getRol().
                nuevoUsuarioEstandar("estandar","estandar123", entidadJuridica);

        Assert.assertTrue(usuarioEstandar.getRol() instanceof UsuarioEstandar);
    }

    @Test
    public void asignacionDeOrganizacionAUsuarioEstandar(){
        Organizacion entidadJuridica = usuarioAdmin.getRol().altaOrganizacionJuridica();
        Usuario usuarioEstandar = usuarioAdmin.getRol().
                nuevoUsuarioEstandar("estandar","estandar123", entidadJuridica);

        Assert.assertTrue(usuarioEstandar.getRol().getOrganizacion() instanceof Organizacion);
    }
}
