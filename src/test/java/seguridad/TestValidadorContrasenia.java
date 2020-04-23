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
    public void contraseniaEnListaNegra(){
        List<String> mensajesDeError = this.validador.ValidarContraseniaLogin("123456789");
        Assert.assertEquals(mensajesDeError.size(), 1);
    }

    @Test
    public void contraseniaEnListaNegraMensajeDeError(){
        List<String> mensajesDeError = this.validador.ValidarContraseniaLogin("123456789");
        Assert.assertEquals(mensajesDeError.get(0), "Contrasenia pertenece a lista negra");
    }

    @Test
    public void contraseniaNoEstaEnListaNegra(){
        List<String> mensajesDeError = this.validador.ValidarContraseniaLogin("c0ntr4SENIAcomL3333ja");
        Assert.assertEquals(mensajesDeError.size(), 0);
    }


}

