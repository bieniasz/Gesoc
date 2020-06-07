package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import usuario.UsuarioAdmin;
import usuario.UsuarioEstandar;
import usuario.Usuario;

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

        Usuario usuario = new Usuario("testUser", "1234567");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void contraseniaMASDe8Caracteres(){

        Usuario usuario = new Usuario("testUser", "123456789");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void contraseniaDe8Caracteres(){

        Usuario usuario = new Usuario("testUser", "12345678");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void contraseniaCortaMensajeDeError(){

        Usuario usuario = new Usuario("testUser", "1234");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals("Contrasenia muy corta, debe tener mas de 8 caracteres", this.errorMessages.get(0));
    }
}
