package usuario;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import organizacion.EntidadJuridica;
import organizacion.Organizacion;

public class TestUsuarioAdmin {

    private UsuarioAdmin usuarioAdmin = new UsuarioAdmin("admin", "admin123" );


    @Test
    public void creacionDeUsuarioEstandar(){

    Usuario usuarioEstandar = usuarioAdmin.nuevoUsuarioEstandar("estandar","estandar123");

    Assert.assertTrue(usuarioEstandar instanceof UsuarioEstandar);
    }

    @Test
    public void creacionDeOrganizacionJuridica(){
        Organizacion entidadJuridica = usuarioAdmin.altaOrganizacionJuridica();

        Assert.assertTrue(entidadJuridica instanceof EntidadJuridica);
    }

    @Test
    public void asignarOrganizacionAUsuarioEstandar(){
        UsuarioEstandar usuarioEstandar = usuarioAdmin.nuevoUsuarioEstandar("estandar","estandar123");
        Organizacion entidadJuridica = usuarioAdmin.altaOrganizacionJuridica();

        usuarioAdmin.asignarOrganizacionAUsuarioEstandar(usuarioEstandar, entidadJuridica);

        Assert.assertTrue(usuarioEstandar.getOrganizacion() instanceof Organizacion);
    }
}
