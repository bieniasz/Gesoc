package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class TestValidadorContrasenia {
    private ValidadorContrasenia validador;

    @Before
    public void init(){
        this.validador = new ValidadorContrasenia();
    }


    @Test
    public void contraseniaNORompeCriterios(){
        List<String> mensajesDeError = this.validador.ValidarContraseniaCreacion("nnKKKKK6456/(%nn");
        Assert.assertEquals(0, mensajesDeError.size());
    }

    @Test
    public void contraseniaRompeUnSoloCriterio(){
        List<String> mensajesDeError = this.validador.ValidarContraseniaCreacion("nnnnnnnnnnnnnnnn&#");
        Assert.assertEquals(1, mensajesDeError.size());
    }

    @Test
    public void contraseniaRompeMultiplesCriterios() {
        List<String> mensajesDeError = this.validador.ValidarContraseniaCreacion("nnnn");
        Assert.assertEquals(3, mensajesDeError.size());
    }
}

