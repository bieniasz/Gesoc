package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

        String contrasenia = "123456789";
        this.criterio.validar(contrasenia, errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void contraseniaNOestaEnListaNegra(){

        String contrasenia = "ContrS3ni$°Segur1s1m4";
        this.criterio.validar(contrasenia, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void criterioListaNegraMensajeDeError(){

        String contrasenia = "123456789";
        this.criterio.validar(contrasenia, errorMessages);

        Assert.assertEquals("Contrasenia pertenece a lista negra", this.errorMessages.get(0));
    }
}
