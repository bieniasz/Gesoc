package domain.entities.seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import domain.entities.seguridad.ContraseniasPrevias.ContraseniasPreviasDAOMemoria;
import domain.entities.seguridad.CriteriosContrasenia.*;
import domain.entities.seguridad.IntentosFallidos.IntentosFallidosDAOMemoria;
import domain.entities.usuario.UserDAO;
import domain.entities.usuario.UserDAOMemoria;
import domain.entities.usuario.Usuario;

import java.util.List;

public class TestValidadorDeUsuario {
    private ValidadorDeUsuario validador;
    private AlmacenContrasenias almancen;
    private Usuario usuario;

    @Before
    public void init(){
        this.almancen = new AlmacenContrasenias();
        this.almancen.setContraseniasPreviasDAO(new ContraseniasPreviasDAOMemoria());
        this.almancen.setIntentosFallidosDAO(new IntentosFallidosDAOMemoria());

        this.usuario = new Usuario();
        this.usuario.setUsuarioId("testUser");
        UserDAO usuarioDao = new UserDAOMemoria();
        usuarioDao.persistirUsuario(this.usuario);
        this.validador = new ValidadorDeUsuario();
        this.validador.setUsuarioDao(usuarioDao);
        this.validador.setAlmacenContrasenias(this.almancen);
    }


    @Test
    public void contraseniaNORompeCriterios(){
        this.validador.agregarCriterioDeCreacionDeContrasenia(new CriterioCaracteresEspeciales());
        List<String> mensajesDeError = this.validador.validarCreacionContrasenia("testUser", "nnKKKKK6456/(%nn");
        Assert.assertEquals(0, mensajesDeError.size());
    }

    @Test
    public void contraseniaRompeUnSoloCriterio(){
        this.validador.agregarCriterioDeCreacionDeContrasenia(new CriterioCaracteresEspeciales());
        this.validador.agregarCriterioDeCreacionDeContrasenia(new CriterioMinusculasYMayusculas());

        List<String> mensajesDeError = this.validador.validarCreacionContrasenia("testUser", "nnnnnnnnnnnnnnnn&#");
        Assert.assertEquals(1, mensajesDeError.size());
    }

    @Test
    public void contraseniaRompeMultiplesCriterios() {
        this.validador.agregarCriterioDeCreacionDeContrasenia(new CriterioCaracteresEspeciales());
        this.validador.agregarCriterioDeCreacionDeContrasenia(new CriterioLongitud());
        this.validador.agregarCriterioDeCreacionDeContrasenia(new CriterioMinusculasYMayusculas());

        List<String> mensajesDeError = this.validador.validarCreacionContrasenia("testUser", "nnnn");
        Assert.assertEquals(3, mensajesDeError.size());
    }

    @Test
    public void validaElAlmacenContrasenias() {
        this.validador.agregarCriterioDeCreacionDeContrasenia(new CriterioRotacionContrasenia(this.almancen));
        this.almancen.registrarContrasenia(this.usuario, "nnKKKKK6456/(%nn");

        List<String> mensajesDeError = this.validador.validarCreacionContrasenia("testUser","nnKKKKK6456/(%nn");
        Assert.assertEquals(1, mensajesDeError.size());
    }

    @Test
    public void validarMetodoContraseniaLoginConError(){
        //TODO: los criterios de login todavia los tengo harcodeados, los tengo que mover a setters
        List<String> errores = this.validador.validarContraseniaLogin("user","password");
        Assert.assertEquals(1,errores.size());
    }

    @Test
    public void validarMetodoContraseniaLoginSinError(){
        this.almancen.registrarContrasenia(this.usuario,"password");
        List<String> errores = this.validador.validarContraseniaLogin("testUser","password");
        Assert.assertEquals(0,errores.size());
    }

    @Test
    public void validarMetodoContraseniaLoginMultiple() throws InterruptedException {
        this.almancen.registrarContrasenia(this.usuario,"password");
        List<String> errores;

        //Intento 1 fallido
        errores = this.validador.validarContraseniaLogin("testUser","Incorrecta");
        Assert.assertEquals(1,errores.size());
        Assert.assertTrue(this.almancen.getIntentosFallidosDeUsuario(this.usuario) != null);

        //Intento 2 fallido
        errores = this.validador.validarContraseniaLogin("testUser","Incorrecta");
        Assert.assertEquals(1,errores.size());

        //Intento 3 fallido
        errores = this.validador.validarContraseniaLogin("testUser","Incorrecta");
        Assert.assertEquals(1,errores.size());
        Assert.assertEquals(3,this.almancen.getIntentosFallidosDeUsuario(usuario).getCantidadIntentos());

        //Intento 4 correcto sin tiempo de espera
        errores = this.validador.validarContraseniaLogin("testUser","password");
        Assert.assertEquals(1,errores.size());
        System.out.println(errores);

        //Intento 4 correcto con tiempo de espera
        Thread.sleep(5000);
        errores = this.validador.validarContraseniaLogin("testUser","password");
        Assert.assertEquals(0,errores.size());
    }
}

