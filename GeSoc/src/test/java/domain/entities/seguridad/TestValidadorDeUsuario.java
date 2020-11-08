package domain.entities.seguridad;

import domain.entities.seguridad.excepciones.LoginBloqueadoTemporalmenteException;
import domain.entities.seguridad.excepciones.UsuarioContraseniaInvalidosException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import db.DAOs.ContraseniasPreviasDAOMemoria;
import domain.entities.seguridad.CriteriosContrasenia.*;
import db.DAOs.IntentosFallidosDAOMemoria;
import db.DAOs.UserDAO;
import db.DAOs.UserDAOMemoria;
import domain.entities.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class TestValidadorDeUsuario {
    private ValidadorDeUsuario validador;
    private AlmacenContrasenias almacen;
    private Usuario usuario;
    UserDAO usuarioDao;
    LocalDateTime horaIntento;

    @Before
    public void init(){
        this.almacen = new AlmacenContrasenias();
        this.almacen.setContraseniasPreviasDAO(new ContraseniasPreviasDAOMemoria());
        this.almacen.setIntentosFallidosDAO(new IntentosFallidosDAOMemoria());

        this.usuario = new Usuario();
        this.usuario.setUsuarioId("testUser");
        this.usuario.setContrasenia("password");
        usuarioDao = new UserDAOMemoria();
        usuarioDao.guardarUsuario(this.usuario);
        this.validador = new ValidadorDeUsuario();
        this.validador.setUsuarioDao(usuarioDao);
        this.validador.setAlmacenContrasenias(this.almacen);
        this.horaIntento = LocalDateTime.now();
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
        this.validador.agregarCriterioDeCreacionDeContrasenia(new CriterioRotacionContrasenia(this.almacen));
        this.almacen.registrarContrasenia(this.usuario, "nnKKKKK6456/(%nn");

        List<String> mensajesDeError = this.validador.validarCreacionContrasenia("testUser","nnKKKKK6456/(%nn");
        Assert.assertEquals(1, mensajesDeError.size());
    }

    @Test (expected = UsuarioContraseniaInvalidosException.class)
    public void validarMetodoContraseniaLoginConError() throws LoginBloqueadoTemporalmenteException, UsuarioContraseniaInvalidosException {
        //TODO: los criterios de login todavia los tengo harcodeados, los tengo que mover a setters
        this.validador.validarLogin("user","password", this.horaIntento);
    }

    @Test
    public void validarMetodoContraseniaLoginSinError() throws LoginBloqueadoTemporalmenteException, UsuarioContraseniaInvalidosException {
        this.almacen.registrarContrasenia(this.usuario,"password");
        Usuario usuario = this.validador.validarLogin("testUser","password", this.horaIntento);
        Assert.assertNotNull(usuario);
    }

    @Test
    public void validarMetodoContraseniaLoginMultiple() throws InterruptedException, LoginBloqueadoTemporalmenteException, UsuarioContraseniaInvalidosException {
        this.almacen.registrarContrasenia(this.usuario,"password");
        List<String> errores;
        //Intento 1 fallido
        try {
            this.validador.validarLogin("testUser", "Incorrecta", this.horaIntento);
        } catch (Exception e){
            Assert.assertEquals(UsuarioContraseniaInvalidosException.class, e.getClass());
        }

        //Intento 2 fallido
        try {
            this.validador.validarLogin("testUser", "Incorrecta", this.horaIntento);
        } catch (Exception e){
            Assert.assertEquals(UsuarioContraseniaInvalidosException.class, e.getClass());
        }

        //Intento 3 fallido
        try {
            this.validador.validarLogin("testUser", "Incorrecta", this.horaIntento);
        } catch (Exception e){
            Assert.assertEquals(UsuarioContraseniaInvalidosException.class, e.getClass());
            Usuario usuario = this.usuarioDao.buscarUsuarioPoruserId("testUser");
            Assert.assertEquals(3, this.almacen.getIntentosFallidosDeUsuario(usuario).getIntentosRealizados());
        }

        //Intento 4 correcto sin tiempo de espera
        try {
            this.validador.validarLogin("testUser", "password", this.horaIntento);
        } catch (Exception e){
            Assert.assertEquals(LoginBloqueadoTemporalmenteException.class, e.getClass());
        }

        //Intento 4 correcto con tiempo de espera
        Thread.sleep(5000);
        Usuario usuario = this.validador.validarLogin("testUser", "password", this.horaIntento);
        Assert.assertNotNull(usuario);
    }
}

