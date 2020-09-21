package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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

        this.criterio.validar("testUser", "1234", errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void contraseniaEsRepetida(){

        AlmacenContrasenias.Instancia().registrarContrasenia("testUser", "1234");
        AlmacenContrasenias.Instancia().registrarContrasenia("testUser", "hola");

        this.criterio.validar("testUser", "1234", errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void criterioRotacionContraseniaMensajeError(){

        AlmacenContrasenias.Instancia().registrarContrasenia("testUser", "1234");
        AlmacenContrasenias.Instancia().registrarContrasenia("testUser", "hola");

        this.criterio.validar("testUser", "1234", errorMessages);

        Assert.assertEquals("La contrasenia repite contrasenias viejas", this.errorMessages.get(0));
    }

    @Test
    public void seBorranLasContraseniasMuyViejas(){
        AlmacenContrasenias.Instancia().registrarContrasenia("testUser", "1234");
        AlmacenContrasenias.Instancia().registrarContrasenia("testUser", "hola");
        AlmacenContrasenias.Instancia().registrarContrasenia("testUser", "admin");
        AlmacenContrasenias.Instancia().registrarContrasenia("testUser", "nombreFamiliarCercano");

        this.criterio.validar("testUser", "1234", errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void lasContraseniasNoViejasValidan(){
        AlmacenContrasenias.Instancia().registrarContrasenia("testUser", "1234");
        AlmacenContrasenias.Instancia().registrarContrasenia("testUser", "hola");
        AlmacenContrasenias.Instancia().registrarContrasenia("testUser", "admin");
        AlmacenContrasenias.Instancia().registrarContrasenia("testUser", "nombreFamiliarCercano");

        this.criterio.validar("testUser", "admin", errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void seDiferencianContraseniasDeDistintosUsuarios(){
        AlmacenContrasenias.Instancia().registrarContrasenia("testUserAlfa", "1234");
        AlmacenContrasenias.Instancia().registrarContrasenia("testUserBeta", "hola");

        this.criterio.validar("testUserBeta", "1234", errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }
}
