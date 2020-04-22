package seguridad;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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

        String contrasenia = "hola!$";
        this.criterio.validar(contrasenia, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void caracteresEspecialesEnMedio(){

        String contrasenia = "hol!$a";
        this.criterio.validar(contrasenia, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void caracteresEspecialesAlPrincipio(){

        String contrasenia = "!$hola";
        this.criterio.validar(contrasenia, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void contraseniaSinCaracteresEspeciales(){

        String contrasenia = "hola";
        this.criterio.validar(contrasenia, errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void mensajeContraseniaSinCaracteresEspeciales(){

        String contrasenia = "hola";
        this.criterio.validar(contrasenia, errorMessages);

        Assert.assertEquals("Faltan caracteres especiales", this.errorMessages.get(0));
    }
}
