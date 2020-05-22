package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import usuario.Estandar;
import usuario.Usuario;

import java.util.List;

public class TestValidadorContrasenia {
    private ValidadorContrasenia validador;

    @Before
    public void init(){
        this.validador = new ValidadorContrasenia();
    }


    @Test
    public void contraseniaNORompeCriterios(){

        Usuario usuario = new Estandar("testUser", "nnKKKKK6456/(%nn");
        List<String> mensajesDeError = this.validador.validarCreacionContrasenia(usuario);

        Assert.assertEquals(0, mensajesDeError.size());
    }

    @Test
    public void contraseniaRompeUnSoloCriterio(){

        Usuario usuario = new Estandar("testUser", "nnnnnnnnnnnnnnnn&#");
        List<String> mensajesDeError = this.validador.validarCreacionContrasenia(usuario);
        Assert.assertEquals(1, mensajesDeError.size());
    }

    @Test
    public void contraseniaRompeMultiplesCriterios() {

        Usuario usuario = new Estandar("testUser", "nnnn");
        List<String> mensajesDeError = this.validador.validarCreacionContrasenia(usuario);
        Assert.assertEquals(3, mensajesDeError.size());
    }

    @Test
    public void validaElAlmacenContrasenias() {
        AlmacenContrasenias.Instancia().eliminarContraseniasAlmacenadas();

        Usuario usuario = new Estandar("testUser", "nnKKKKK6456/(%nn");

        List<String> mensajesDeError = this.validador.validarCreacionContrasenia(usuario);
        mensajesDeError = this.validador.validarCreacionContrasenia(usuario);

        Assert.assertEquals(1, mensajesDeError.size());
    }
}

