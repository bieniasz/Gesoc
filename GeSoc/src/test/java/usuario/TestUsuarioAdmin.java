package usuario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import organizacion.EntidadJuridica;
import organizacion.Organizacion;
import seguridad.AlmacenContrasenias;

import java.util.List;

public class TestUsuarioAdmin {

    private Usuario usuarioAdmin;
    private AlmacenContrasenias almacen;

    public TestUsuarioAdmin() throws Exception {
    }

    @Before
    public void init(){
        this.usuarioAdmin = new Usuario();
        this.usuarioAdmin.setUsuarioId("admin12345");
        this.usuarioAdmin.setContrasenia("nn");
        this.usuarioAdmin.setRol(new UsuarioAdmin());
        this.almacen = new AlmacenContrasenias();
    }

    @Test
    public void creacionDeOrganizacionJuridica(){
        Organizacion entidadJuridica = usuarioAdmin.getRol().altaOrganizacionJuridica();

        Assert.assertTrue(entidadJuridica instanceof EntidadJuridica);
    }

  /*  @Test
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
    }*/
/*
    @Test
    public void cambioDeContraseniaEnUsuarioYAlmacen() throws Exception {
        Assert.assertTrue("Admin@12345"== usuarioAdmin.getContrasenia());
        this.usuarioAdmin.getRol().cambiarContrasenia("Admin@54321");
        Assert.assertTrue("Admin@54321" == usuarioAdmin.getContrasenia());

        //Ahora testear si cambió en el usuario del almacen
        List<String> contraseniasPrevias = AlmacenContrasenias.Instancia().getContraseniasPreviasDeUsuario(usuarioAdmin.getUsuario());
        String contraseniaNueva = contraseniasPrevias.get(contraseniasPrevias.size()-1);

        Assert.assertTrue(contraseniaNueva == "Admin@54321");
    }*/

}