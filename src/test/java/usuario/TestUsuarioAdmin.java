package usuario;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import organizacion.EntidadJuridica;
import organizacion.Organizacion;
import seguridad.AlmacenContrasenias;
import usuario.Usuario;
import usuario.UsuarioAdmin;

public class TestUsuarioAdmin {

    private Usuario usuarioAdmin = new Usuario("admin12345", "Admin@12345");

    public TestUsuarioAdmin() throws Exception {
    }

    @Before
    public void init(){
        usuarioAdmin.setRol(new UsuarioAdmin());
        AlmacenContrasenias.Instancia().eliminarContraseniasAlmacenadas();
        AlmacenContrasenias.Instancia().setPeriodosDeRotacion(3);
    }

    @Test
    public void creacionDeOrganizacionJuridica(){
        Organizacion entidadJuridica = usuarioAdmin.getRol().altaOrganizacionJuridica();

        Assert.assertTrue(entidadJuridica instanceof EntidadJuridica);
    }

    @Test
    public void creacionDeUsuarioEstandar() throws Exception {
        Organizacion entidadJuridica = usuarioAdmin.getRol().altaOrganizacionJuridica();
        Usuario usuarioEstandar = usuarioAdmin.getRol().
                nuevoUsuarioEstandar("estandar123","Estandar@123", entidadJuridica);

        Assert.assertTrue(usuarioEstandar.getRol() instanceof UsuarioEstandar);
    }

    @Test
    public void asignacionDeOrganizacionAUsuarioEstandar() throws Exception {
        Organizacion entidadJuridica = usuarioAdmin.getRol().altaOrganizacionJuridica();
        Usuario usuarioEstandar = usuarioAdmin.getRol().
                nuevoUsuarioEstandar("estandar123","Estandar@123", entidadJuridica);

        Assert.assertTrue(usuarioEstandar.getRol().getOrganizacion() instanceof Organizacion);
    }
}
