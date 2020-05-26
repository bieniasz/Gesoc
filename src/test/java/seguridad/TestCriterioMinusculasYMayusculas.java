package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import usuario.UsuarioAdmin;
import usuario.UsuarioEstandar;
import usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class TestCriterioMinusculasYMayusculas {

    private CriterioMinusculasYMayusculas criterio;
    private List<String> errorMessages;
    private UsuarioAdmin usuarioAdmin = new UsuarioAdmin("admin", "admin123" );

    @Before
    public void init(){

        this.criterio = new CriterioMinusculasYMayusculas();
        this.errorMessages = new ArrayList<String>();
    }

    @Test
    public void contraseniaNoTieneMayusculas(){

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar("testUser", "nnnnnnnnnnnn");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void contraseniaNoTieneMayusculasMensajeDeError(){

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar("testUser", "nnnnnnnnnnnn");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals("Faltan letras mayusculas a la contrasenia", this.errorMessages.get(0));
    }

    @Test
    public void contraseniaNoTieneMinusculas(){

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar("testUser", "NNNNNN");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void contraseniaNoTieneMinusculasMensajeDeError(){

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar("testUser", "NNNNNN");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals("Faltan letras minusculas a la contrasenia", this.errorMessages.get(0));
    }

    @Test
    public void contraseniaNoTieneMinuscuYMayusculasOrdenadas1(){

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar("testUser", "nnnnnnNNNNNN");
        this.criterio.validar(usuario, errorMessages);
;
        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void contraseniaNoTieneMinuscuYMayusculasOrdenadas2(){

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar("testUser", "NNNnnnnn");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void contraseniaNoTieneMinuscuYMayusculasMixeadas(){

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar("testUser", "nNnNn");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }
}
