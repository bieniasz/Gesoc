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
    public void contraseniaMuyCorta(){
        List<String> mensajesDeError = this.validador.ValidarContraseniaLogin("hola");
        Assert.assertEquals(mensajesDeError.size(), 1);
    }

    @Test
    public void contraseniaCortaMensaje(){
        List<String> mensajesDeError = this.validador.ValidarContraseniaLogin("hola");
        Assert.assertEquals(mensajesDeError.get(0), "Contrasenia muy corta, debe tener mas de 8 letras");
    }

    @Test
    public void contraseniaTamanioCorrecto(){
        List<String> mensajesDeError = this.validador.ValidarContraseniaLogin("masDeOchoLetras");
        Assert.assertEquals(mensajesDeError.size(), 0);
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

    @Test
    public void contraseniaNoTieneMayusculas(){
        List<String> mensajesDeError = this.validador.ValidarContraseniaCreacion("nnnnnnnnnnnnn");
        Assert.assertEquals(mensajesDeError.size(), 1);
    }

    @Test
    public void contraseniaNoTieneMayusculasMensaje(){
        List<String> mensajesDeError = this.validador.ValidarContraseniaCreacion("nnnnnnnnnnnnn");
        Assert.assertEquals(mensajesDeError.get(0), "Faltan letras mayusculas a la contrasenia");
    }

    @Test
    public void contraseniaNoTieneMinusculas(){
        List<String> mensajesDeError = this.validador.ValidarContraseniaCreacion("NNNNNNNNNNNNNN");
        Assert.assertEquals(mensajesDeError.size(), 1);
    }

    @Test
    public void contraseniaNoTieneMinusculasMensaje(){
        List<String> mensajesDeError = this.validador.ValidarContraseniaCreacion("NNNNNNNNNNNNNN");
        Assert.assertEquals(mensajesDeError.get(0), "Faltan letras minusculas a la contrasenia");
    }
}

