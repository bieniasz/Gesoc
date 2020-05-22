package seguridad;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import usuario.Estandar;
import usuario.Usuario;

import java.util.ArrayList;
import java.util.List;


public class TestCriterioCaracteresEspeciales {

    private CriterioCaracteresEspeciales criterio;
    private List<String> errorMessages;
    private final String nombreUsuario = "testUser";

    @Before
    public void init(){

        this.criterio = new CriterioCaracteresEspeciales();
        this.errorMessages = new ArrayList<String>();
    }

    @Test
    public void caracteresEspecialesAlFinal(){

        Usuario usuario = new Estandar(nombreUsuario, "hola!$");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void caracteresEspecialesEnMedio(){

        Usuario usuario = new Estandar(nombreUsuario, "hol!$a");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void caracteresEspecialesAlPrincipio(){

        Usuario usuario = new Estandar(nombreUsuario, "!$hola");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void contraseniaSinCaracteresEspeciales(){

        Usuario usuario = new Estandar(nombreUsuario, "hola");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void mensajeContraseniaSinCaracteresEspeciales(){

        Usuario usuario = new Estandar(nombreUsuario, "hola");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals("Faltan caracteres especiales", this.errorMessages.get(0));
    }
}
