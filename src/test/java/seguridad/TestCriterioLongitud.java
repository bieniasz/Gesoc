package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestCriterioLongitud {

    private CriterioLongitud criterio;
    private List<String> errorMessages;

    @Before
    public void init(){

        this.criterio = new CriterioLongitud();
        this.errorMessages = new ArrayList<String>();
    }

    @Test
    public void contraseniaMENOSDe8Caracteres(){

        String contrasenia = "1234567";
        this.criterio.validar(contrasenia, errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void contraseniaMASDe8Caracteres(){

        String contrasenia = "123456789";
        this.criterio.validar(contrasenia, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void contraseniaDe8Caracteres(){

        String contrasenia = "12345678";
        this.criterio.validar(contrasenia, errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void contraseniaCortaMensajeDeError(){

        String contrasenia = "1234";
        this.criterio.validar(contrasenia, errorMessages);

        Assert.assertEquals("Contrasenia muy corta, debe tener mas de 8 caracteres", this.errorMessages.get(0));
    }
}
