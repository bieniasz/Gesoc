package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class TestCriterioListaNegra {

    private CriterioFueraListaNegra criterio;
    private List<String> errorMessages;

    @Before
    public void init(){

        this.criterio = new CriterioFueraListaNegra();
        this.errorMessages = new ArrayList<String>();
    }

    @Test
    public void contraseniaEnListaNegra(){

        this.criterio.validar("testUser","123456" , errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void contraseniaNOestaEnListaNegra(){

        this.criterio.validar("testUser", "ContrS3ni$°Segur1s1m4", errorMessages);;

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void criterioListaNegraMensajeDeError(){

        this.criterio.validar("testUser", "123456", errorMessages);;

        Assert.assertEquals("Contrasenia pertenece a lista negra", this.errorMessages.get(0));
    }
}
