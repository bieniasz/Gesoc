package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import usuario.Usuario;

import java.util.List;

public class TestValidadorDeUsuario {
    private ValidadorDeUsuario validador;

    @Before
    public void init(){
        this.validador = new ValidadorDeUsuario();
        AlmacenContrasenias.Instancia().eliminarContraseniasAlmacenadas();
        AlmacenContrasenias.Instancia().eliminarIntentosFallidosAlmacenados();
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

    @Test
    public void validarMetodoContraseniaLoginConError(){

        List<String> errores = this.validador.validarContraseniaLogin("user","password");

        Assert.assertEquals(1,errores.size());

    }

    @Test
    public void validarMetodoContraseniaLoginSinError(){
        AlmacenContrasenias.Instancia().registrarContrasenia("user","password");
        List<String> errores = this.validador.validarContraseniaLogin("user","password");

        Assert.assertEquals(0,errores.size());
    }

    @Test
    public void validarMetodoContraseniaLoginMultiple() throws InterruptedException {
        AlmacenContrasenias.Instancia().registrarContrasenia("user","password");
        List<String> errores;

        //Intento 1 fallido
        errores = this.validador.validarContraseniaLogin("user","Incorrecta");
        Assert.assertEquals(1,errores.size());
        Assert.assertTrue(AlmacenContrasenias.Instancia().getIntentosFallidosDeUsuario("user") != null);

        //Intento 2 fallido
        errores = this.validador.validarContraseniaLogin("user","Incorrecta");
        Assert.assertEquals(1,errores.size());

        //Intento 3 fallido
        errores = this.validador.validarContraseniaLogin("user","Incorrecta");
        Assert.assertEquals(1,errores.size());
        Assert.assertEquals(3,AlmacenContrasenias.Instancia().getIntentosFallidosDeUsuario("user").getCantidadIntentos());

        //Intento 4 correcto sin tiempo de espera
        errores = this.validador.validarContraseniaLogin("user","password");
        Assert.assertEquals(1,errores.size());
        //System.out.println(errores);

        //Intento 4 correcto con tiempo de espera
        Thread.sleep(5000);
        errores = this.validador.validarContraseniaLogin("user","password");
        Assert.assertEquals(0,errores.size());
    }

    @Test
    public void validarMetodoCreacionDeUsuario() throws Exception {
        Usuario usuario = validador.crearUsuario("usuario123","Contraseña1234@");

        Assert.assertTrue(usuario instanceof Usuario);
    }

    @Test
    public void metodoCreacionDeusuarioConError() throws Exception {
        try {
            Usuario usuario = validador.crearUsuario("usuario123","contraseña1234@");
        } catch (Exception e) {
            Assert.assertTrue(true);
            return;
        }
        Assert.assertTrue(false);
    }
}

