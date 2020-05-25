package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import usuario.UsuarioEstandar;
import usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class TestCriterioRotacionContrasenia {

    private CriterioRotacionContrasenia criterio;
    private List<String> errorMessages;

    @Before
    public void init(){

        this.criterio = new CriterioRotacionContrasenia();
        this.errorMessages = new ArrayList<String>();
        AlmacenContrasenias.Instancia().eliminarContraseniasAlmacenadas();
        AlmacenContrasenias.Instancia().setPeriodosDeRotacion(3);
    }

    @Test
    public void contraseniaEsNueva(){

        Usuario usuario = new UsuarioEstandar("testUser", "1234");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void contraseniaEsRepetida(){

        AlmacenContrasenias.Instancia().registrarContrasenia(new UsuarioEstandar("testUser", "1234"));
        AlmacenContrasenias.Instancia().registrarContrasenia(new UsuarioEstandar("testUser", "hola"));

        Usuario usuario = new UsuarioEstandar("testUser", "1234");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void criterioRotacionContraseniaMensajeError(){

        AlmacenContrasenias.Instancia().registrarContrasenia(new UsuarioEstandar("testUser", "1234"));
        AlmacenContrasenias.Instancia().registrarContrasenia(new UsuarioEstandar("testUser", "hola"));

        Usuario usuario = new UsuarioEstandar("testUser", "1234");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals("La contrasenia repite contrasenias viejas", this.errorMessages.get(0));
    }

    @Test
    public void seBorranLasContraseniasMuyViejas(){
        AlmacenContrasenias.Instancia().registrarContrasenia(new UsuarioEstandar("testUser", "1234"));
        AlmacenContrasenias.Instancia().registrarContrasenia(new UsuarioEstandar("testUser", "hola"));
        AlmacenContrasenias.Instancia().registrarContrasenia(new UsuarioEstandar("testUser", "admin"));
        AlmacenContrasenias.Instancia().registrarContrasenia(new UsuarioEstandar("testUser", "nombreFamiliarCercano"));

        Usuario usuario = new UsuarioEstandar("testUser", "1234");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void lasContraseniasNoViejasValidan(){
        AlmacenContrasenias.Instancia().registrarContrasenia(new UsuarioEstandar("testUser", "1234"));
        AlmacenContrasenias.Instancia().registrarContrasenia(new UsuarioEstandar("testUser", "hola"));
        AlmacenContrasenias.Instancia().registrarContrasenia(new UsuarioEstandar("testUser", "admin"));
        AlmacenContrasenias.Instancia().registrarContrasenia(new UsuarioEstandar("testUser", "nombreFamiliarCercano"));

        Usuario usuario = new UsuarioEstandar("testUser", "admin");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void seDiferencianContraseniasDeDistintosUsuarios(){
        AlmacenContrasenias.Instancia().registrarContrasenia(new UsuarioEstandar("testUserAlfa", "1234"));
        AlmacenContrasenias.Instancia().registrarContrasenia(new UsuarioEstandar("testUserBeta", "hola"));

        Usuario usuario = new UsuarioEstandar("testUserBeta", "1234");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }
}
