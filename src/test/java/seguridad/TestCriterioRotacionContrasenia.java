package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import usuario.Estandar;
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
    }

    @Test
    public void contraseniaEsNueva(){

        Usuario usuario = new Estandar("testUser", "1234");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void contraseniaEsRepetida(){

        AlmacenContrasenias.Instancia().registrarContrasenia(new Estandar("testUser", "1234"));
        AlmacenContrasenias.Instancia().registrarContrasenia(new Estandar("testUser", "hola"));

        Usuario usuario = new Estandar("testUser", "1234");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void criterioRotacionContraseniaMensajeError(){

        AlmacenContrasenias.Instancia().registrarContrasenia(new Estandar("testUser", "1234"));
        AlmacenContrasenias.Instancia().registrarContrasenia(new Estandar("testUser", "hola"));

        Usuario usuario = new Estandar("testUser", "1234");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals("La contrasenia repite contrasenias viejas", this.errorMessages.get(0));
    }

    @Test
    public void seBorranLasContraseniasMuyViejas(){
        AlmacenContrasenias.Instancia().registrarContrasenia(new Estandar("testUser", "1234"));
        AlmacenContrasenias.Instancia().registrarContrasenia(new Estandar("testUser", "hola"));
        AlmacenContrasenias.Instancia().registrarContrasenia(new Estandar("testUser", "admin"));
        AlmacenContrasenias.Instancia().registrarContrasenia(new Estandar("testUser", "nombreFamiliarCercano"));

        Usuario usuario = new Estandar("testUser", "1234");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void lasContraseniasNoViejasValidan(){
        AlmacenContrasenias.Instancia().registrarContrasenia(new Estandar("testUser", "1234"));
        AlmacenContrasenias.Instancia().registrarContrasenia(new Estandar("testUser", "hola"));
        AlmacenContrasenias.Instancia().registrarContrasenia(new Estandar("testUser", "admin"));
        AlmacenContrasenias.Instancia().registrarContrasenia(new Estandar("testUser", "nombreFamiliarCercano"));

        Usuario usuario = new Estandar("testUser", "admin");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void seDiferencianContraseniasDeDistintosUsuarios(){
        AlmacenContrasenias.Instancia().registrarContrasenia(new Estandar("testUserAlfa", "1234"));
        AlmacenContrasenias.Instancia().registrarContrasenia(new Estandar("testUserBeta", "hola"));

        Usuario usuario = new Estandar("testUserBeta", "1234");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }
}
