package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import usuario.UsuarioAdmin;
import usuario.UsuarioEstandar;
import usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class TestCriterioRotacionContrasenia {

    private CriterioRotacionContrasenia criterio;
    private List<String> errorMessages;
    private UsuarioAdmin usuarioAdmin = new UsuarioAdmin("admin", "admin123" );

    @Before
    public void init(){

        this.criterio = new CriterioRotacionContrasenia();
        this.errorMessages = new ArrayList<String>();
        AlmacenContrasenias.Instancia().eliminarContraseniasAlmacenadas();
        AlmacenContrasenias.Instancia().setPeriodosDeRotacion(3);
    }

    @Test
    public void contraseniaEsNueva(){

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar("testUser", "1234");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void contraseniaEsRepetida(){

        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioAdmin.nuevoUsuarioEstandar("testUser", "1234"));
        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioAdmin.nuevoUsuarioEstandar("testUser", "hola"));

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar("testUser", "1234");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void criterioRotacionContraseniaMensajeError(){

        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioAdmin.nuevoUsuarioEstandar("testUser", "1234"));
        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioAdmin.nuevoUsuarioEstandar("testUser", "hola"));

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar("testUser", "1234");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals("La contrasenia repite contrasenias viejas", this.errorMessages.get(0));
    }

    @Test
    public void seBorranLasContraseniasMuyViejas(){
        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioAdmin.nuevoUsuarioEstandar("testUser", "1234"));
        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioAdmin.nuevoUsuarioEstandar("testUser", "hola"));
        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioAdmin.nuevoUsuarioEstandar("testUser", "admin"));
        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioAdmin.nuevoUsuarioEstandar("testUser", "nombreFamiliarCercano"));

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar("testUser", "1234");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void lasContraseniasNoViejasValidan(){
        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioAdmin.nuevoUsuarioEstandar("testUser", "1234"));
        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioAdmin.nuevoUsuarioEstandar("testUser", "hola"));
        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioAdmin.nuevoUsuarioEstandar("testUser", "admin"));
        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioAdmin.nuevoUsuarioEstandar("testUser", "nombreFamiliarCercano"));

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar("testUser", "admin");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void seDiferencianContraseniasDeDistintosUsuarios(){
        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioAdmin.nuevoUsuarioEstandar("testUserAlfa", "1234"));
        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioAdmin.nuevoUsuarioEstandar("testUserBeta", "hola"));

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar("testUserBeta", "1234");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }
}
