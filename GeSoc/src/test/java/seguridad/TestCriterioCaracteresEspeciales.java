package seguridad;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import seguridad.CriteriosContrasenia.CriterioCaracteresEspeciales;
import usuario.Usuario;

import java.util.ArrayList;
import java.util.List;


public class TestCriterioCaracteresEspeciales {

    private CriterioCaracteresEspeciales criterio;
    private List<String> errorMessages;

    @Before
    public void init(){

        this.criterio = new CriterioCaracteresEspeciales();
        this.errorMessages = new ArrayList<String>();
    }

    @Test
    public void caracteresEspecialesAlFinal(){

        Usuario usuario = new Usuario();
        usuario.setUsuarioId("testUser");
        this.criterio.validar(usuario,"hola!$" , errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void caracteresEspecialesEnMedio(){

        Usuario usuario = new Usuario();
        this.criterio.validar(usuario, "hol!$a", errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void caracteresEspecialesAlPrincipio(){

        Usuario usuario = new Usuario();
        this.criterio.validar(usuario, "!$hola", errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void contraseniaSinCaracteresEspeciales(){

        Usuario usuario = new Usuario();
        this.criterio.validar(usuario, "hola", errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void mensajeContraseniaSinCaracteresEspeciales(){

        Usuario usuario = new Usuario();
        this.criterio.validar(usuario, "hola", errorMessages);

        Assert.assertEquals("Faltan caracteres especiales", this.errorMessages.get(0));
    }
}
