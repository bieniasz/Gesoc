package db;

import db.DAOs.*;
import domain.entities.seguridad.AlmacenContrasenias;
import domain.entities.seguridad.ValidadorDeUsuario;
import domain.entities.seguridad.excepciones.LoginBloqueadoTemporalmenteException;
import domain.entities.seguridad.excepciones.UsuarioContraseniaInvalidosException;
import domain.entities.usuario.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class TestValidadorUsuarioDAOMySQL {
    private ValidadorDeUsuario validador;
    private AlmacenContrasenias almacen;
    private UserDAO usuarioDao;
    private LocalDateTime horaIntento;

    @Before
    public void init(){
        this.almacen = new AlmacenContrasenias();
        this.almacen.setContraseniasPreviasDAO(new ContraseniasPreviasDAOMySQL());
        this.almacen.setIntentosFallidosDAO(new IntentosFallidosDAOMySQL());
        usuarioDao = new UserDAOMySQL();

        Usuario usuario = usuarioDao.buscarUsuarioPoruserId("aos");
        new IntentosFallidosDAOMySQL().eliminarIntentoFallido(usuario);

        this.validador = new ValidadorDeUsuario();
        this.validador.setUsuarioDao(usuarioDao);
        this.validador.setAlmacenContrasenias(this.almacen);

        this.horaIntento = LocalDateTime.now();
    }

    @Test (expected = UsuarioContraseniaInvalidosException.class)
    public void validarMetodoContraseniaLoginConError() throws LoginBloqueadoTemporalmenteException, UsuarioContraseniaInvalidosException {
        //TODO: los criterios de login todavia los tengo harcodeados, los tengo que mover a setters
        this.validador.validarLogin("aos","password", this.horaIntento);
    }

    @Test
    public void validarMetodoContraseniaLoginSinError() throws LoginBloqueadoTemporalmenteException, UsuarioContraseniaInvalidosException {
        //this.almacen.registrarContrasenia(this.usuario,"password");
        Usuario user = this.validador.validarLogin("aos","aos123", this.horaIntento);
        Assert.assertNotNull(user);
    }

    @Test
    public void validarMetodoContraseniaLoginMultiple() throws InterruptedException, LoginBloqueadoTemporalmenteException, UsuarioContraseniaInvalidosException {
        //this.almacen.registrarContrasenia(this.usuario,"password");
        List<String> errores;

        //Intento 1 fallido
        try {
            this.validador.validarLogin("aos", "Incorrecta", this.horaIntento);
        } catch (Exception e){
            Assert.assertEquals(UsuarioContraseniaInvalidosException.class, e.getClass());
        }

        //Intento 2 fallido
        try {
            this.validador.validarLogin("aos", "Incorrecta", this.horaIntento);
        } catch (Exception e){
            Assert.assertEquals(UsuarioContraseniaInvalidosException.class, e.getClass());
        }

        //Intento 3 fallido
        try {
            this.validador.validarLogin("aos", "Incorrecta", this.horaIntento);
        } catch (Exception e){
            Assert.assertEquals(UsuarioContraseniaInvalidosException.class, e.getClass());
            Usuario usuario = this.usuarioDao.buscarUsuarioPoruserId("aos");
            Assert.assertEquals(3,this.almacen.getIntentosFallidosDeUsuario(usuario).getIntentosRealizados());
        }

        //Intento 4 correcto sin tiempo de espera
        try {
            this.validador.validarLogin("aos", "aos123", this.horaIntento);
        } catch (Exception e){
            Assert.assertEquals(LoginBloqueadoTemporalmenteException.class, e.getClass());
        }

        //Intento 4 correcto con tiempo de espera
        Thread.sleep(5000);
        Usuario usuario = this.validador.validarLogin("aos", "aos123", this.horaIntento);
        Assert.assertNotNull(usuario);
    }
}
