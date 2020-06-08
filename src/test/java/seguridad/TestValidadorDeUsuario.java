package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestValidadorDeUsuario {
    private ValidadorDeUsuario validador;

    @Before
    public void init(){
        this.validador = new ValidadorDeUsuario();
    }


    @Test
    public void contraseniaNORompeCriterios(){

       List<String> mensajesDeError = this.validador.validarCreacionContrasenia("testUser", "nnKKKKK6456/(%nn");

        Assert.assertEquals(0, mensajesDeError.size());
    }

    @Test
    public void contraseniaRompeUnSoloCriterio(){

        List<String> mensajesDeError = this.validador.validarCreacionContrasenia("testUser", "nnnnnnnnnnnnnnnn&#");
        Assert.assertEquals(1, mensajesDeError.size());
    }

    @Test
    public void contraseniaRompeMultiplesCriterios() {

        List<String> mensajesDeError = this.validador.validarCreacionContrasenia("testUser", "nnnn");
        Assert.assertEquals(3, mensajesDeError.size());
    }

    @Test
    public void validaElAlmacenContrasenias() {
        AlmacenContrasenias.Instancia().eliminarContraseniasAlmacenadas();

        List<String> mensajesDeError = this.validador.validarCreacionContrasenia("testUser","nnKKKKK6456/(%nn");
        mensajesDeError = this.validador.validarCreacionContrasenia("testUser","nnKKKKK6456/(%nn");

        Assert.assertEquals(1, mensajesDeError.size());
    }
}

