package domain.entities.seguridad;

import domain.entities.seguridad.excepciones.LoginBloqueadoTemporalmenteException;
import domain.entities.seguridad.excepciones.UsuarioContraseniaInvalidosException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import sun.font.TrueTypeFont;
import db.DAOs.ContraseniasPreviasDAOMemoria;
import domain.entities.seguridad.CriteriosLogin.CriterioLogin;
import domain.entities.seguridad.CriteriosLogin.CriterioTiempoLogin;
import domain.entities.seguridad.IntentosFallidos.IntentosFallidos;
import db.DAOs.IntentosFallidosDAOMemoria;
import domain.entities.usuario.Usuario;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;

public class TestCriterioTiempoLogin {

    private CriterioTiempoLogin criterio;
    private List<String> errorMessages;
    private AlmacenContrasenias almacen;
    private Usuario usuarioQueExiste;

    @Before
    public void init() {

        this.errorMessages = new ArrayList<String>();
        this.almacen = new AlmacenContrasenias();
        this.almacen.setIntentosFallidosDAO(new IntentosFallidosDAOMemoria());
        this.almacen.setContraseniasPreviasDAO(new ContraseniasPreviasDAOMemoria());
        this.criterio = new CriterioTiempoLogin(this.almacen);

        this.usuarioQueExiste = new Usuario();
        this.usuarioQueExiste.setUsuarioId("user");
        this.usuarioQueExiste.setContrasenia("password");
        this.usuarioQueExiste.setId(1);
    }

    @Test
    public void primerErrorAlLogearConUsuarioNoExistente() {
        Usuario usuarioQueNoExiste = new Usuario();
        usuarioQueNoExiste.setUsuarioId("usuarioNoExiste");

        this.criterio.errorAlLogear(usuarioQueNoExiste);
        IntentosFallidos intentosFallidos = this.almacen.getIntentosFallidosDeUsuario(usuarioQueNoExiste);

        if (intentosFallidos == null){
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    @Test(expected = LoginBloqueadoTemporalmenteException.class)
    public void NoCumpleCondicionDeEspera() throws InterruptedException, UsuarioContraseniaInvalidosException, LoginBloqueadoTemporalmenteException {
        List<String> errores = new ArrayList<>();
        String passCorrecta = "password",
                passIncorrecta = "password1";
        this.almacen.registrarContrasenia(usuarioQueExiste, passCorrecta);


        // INTENTO 1
        try {
            new CriterioLogin(this.almacen).validar(usuarioQueExiste, passIncorrecta, errores);
        } catch (Exception e) {
            IntentosFallidos intentosFallidos1 = this.almacen.getIntentosFallidosDeUsuario(usuarioQueExiste);
            Assert.assertEquals(1, intentosFallidos1.getIntentosRealizados());
            Assert.assertNull(intentosFallidos1.getHoraDelIntentoMaximo());
        }

        // INTENTO 2
        Thread.sleep(1000);
        try {
            new CriterioLogin(this.almacen).validar(usuarioQueExiste, passIncorrecta, errores);
        } catch (Exception e) {
            IntentosFallidos intentosFallidos2 = this.almacen.getIntentosFallidosDeUsuario(usuarioQueExiste);
            Assert.assertEquals(2, intentosFallidos2.getIntentosRealizados());
            Assert.assertNull(intentosFallidos2.getHoraDelIntentoMaximo());
        }

        // INTENTO 3 debe guardar el tiempo en horaDeIntentoMaximo
        Thread.sleep(1000);
        LocalDateTime horaTercerItento = LocalDateTime.now();

        try {
            new CriterioLogin(this.almacen).validar(usuarioQueExiste, passIncorrecta, errores);
        } catch (Exception e) {
            IntentosFallidos intentosFallidos3 = this.almacen.getIntentosFallidosDeUsuario(usuarioQueExiste);
            Assert.assertEquals(intentosFallidos3.getIntentosRealizados(), 3);
            Assert.assertEquals(Duration.between(intentosFallidos3.getHoraDelIntentoMaximo(), horaTercerItento).getSeconds(), 0);
        }

        //INTENTO 4
        new CriterioLogin(this.almacen).validar(usuarioQueExiste, passCorrecta, errores);
        this.criterio.validar(this.usuarioQueExiste,  passCorrecta, errores);
    }

    @Test
    public void cumpleCondicionDeEspera() throws InterruptedException, UsuarioContraseniaInvalidosException, LoginBloqueadoTemporalmenteException {
        List<String> errores = new ArrayList<>();
        String passCorrecta = "password",
                passIncorrecta = "password1";
        this.almacen.registrarContrasenia(usuarioQueExiste, passCorrecta);


        // INTENTO 1
        try {
            new CriterioLogin(this.almacen).validar(usuarioQueExiste, passIncorrecta, errores);
        } catch (Exception e) {
            IntentosFallidos intentosFallidos1 = this.almacen.getIntentosFallidosDeUsuario(usuarioQueExiste);
            Assert.assertEquals(1, intentosFallidos1.getIntentosRealizados());
            Assert.assertNull(intentosFallidos1.getHoraDelIntentoMaximo());
        }

        // INTENTO 2
        Thread.sleep(1000);
        try {
            new CriterioLogin(this.almacen).validar(usuarioQueExiste, passIncorrecta, errores);
        } catch (Exception e) {
            IntentosFallidos intentosFallidos2 = this.almacen.getIntentosFallidosDeUsuario(usuarioQueExiste);
            Assert.assertEquals(2, intentosFallidos2.getIntentosRealizados());
            Assert.assertNull(intentosFallidos2.getHoraDelIntentoMaximo());
        }

        // INTENTO 3 debe guardar el tiempo en horaDeIntentoMaximo
        Thread.sleep(1000);
        try {
            new CriterioLogin(this.almacen).validar(usuarioQueExiste, passIncorrecta, errores);
        } catch (Exception e) {
            LocalDateTime horaTercerItento = LocalDateTime.now();
            IntentosFallidos intentosFallidos3 = this.almacen.getIntentosFallidosDeUsuario(usuarioQueExiste);
            Assert.assertEquals(intentosFallidos3.getIntentosRealizados(), 3);
            Assert.assertEquals(Duration.between(intentosFallidos3.getHoraDelIntentoMaximo(), horaTercerItento).getSeconds(), 0);
        }

        //INTENTO 4
        Thread.sleep(5000);
        new CriterioLogin(this.almacen).validar(usuarioQueExiste, passCorrecta, errores);
        this.criterio.validar(this.usuarioQueExiste,  passCorrecta, errores);
    }
}
