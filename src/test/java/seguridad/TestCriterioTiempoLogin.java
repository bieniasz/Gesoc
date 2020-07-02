package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.font.TrueTypeFont;
import usuario.Usuario;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;

public class TestCriterioTiempoLogin {

    private CriterioTiempoLogin criterio;
    private List<String> errorMessages;

    @Before
    public void init() {

        this.criterio = new CriterioTiempoLogin();
        this.errorMessages = new ArrayList<String>();
        AlmacenContrasenias.Instancia().eliminarContraseniasAlmacenadas();
    }

    @Test
    public void primerErrorAlLogearConUsuarioExistente() {
        String usuarioQueExiste = "user";
        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioQueExiste,"password");
        new CriterioLogin().validar(usuarioQueExiste,"password",new ArrayList<String>());
        criterio.errorAlLogear(usuarioQueExiste);
        IntentosFallidos intentosFallidos = AlmacenContrasenias.Instancia().getIntentosFallidosDeUsuario(usuarioQueExiste);
        Assert.assertEquals(intentosFallidos.getCantidadIntentos(), 1);
    }

    @Test
    public void primerErrorAlLogearConUsuarioNoExistente() {

        String usuarioQueNoExiste = "usuarioNoExiste";
        criterio.errorAlLogear(usuarioQueNoExiste);
        IntentosFallidos intentosFallidos = AlmacenContrasenias.Instancia().getIntentosFallidosDeUsuario(usuarioQueNoExiste);

        if (intentosFallidos == null){
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    @Test
    public void errorAlLogearMultiple() throws InterruptedException {
        String usuarioQueExiste = "user";
        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioQueExiste,"password");
        new CriterioLogin().validar(usuarioQueExiste,"password",new ArrayList<String>());
        // INTENTO 1

        criterio.errorAlLogear(usuarioQueExiste);
        LocalDateTime horaPrimerItento = LocalDateTime.now();

        IntentosFallidos intentosFallidos1 = AlmacenContrasenias.Instancia().getIntentosFallidosDeUsuario(usuarioQueExiste);
        Assert.assertEquals(intentosFallidos1.getCantidadIntentos(), 1);
        Assert.assertTrue(intentosFallidos1.getHoraDelIntentoMaximo() == null);

        // INTENTO 2
        Thread.sleep(1000);
        criterio.errorAlLogear(usuarioQueExiste);
        LocalDateTime horaSegundoItento = LocalDateTime.now();

        IntentosFallidos intentosFallidos2 = AlmacenContrasenias.Instancia().getIntentosFallidosDeUsuario(usuarioQueExiste);
        Assert.assertEquals(intentosFallidos2.getCantidadIntentos(), 2);
        Assert.assertTrue(intentosFallidos2.getHoraDelIntentoMaximo() == null);

        // INTENTO 3 debe guardar el tiempo en horaDeIntentoMaximo
        Thread.sleep(1000);
        criterio.errorAlLogear(usuarioQueExiste);
        LocalDateTime horaTercerItento = LocalDateTime.now();

        IntentosFallidos intentosFallidos3 = AlmacenContrasenias.Instancia().getIntentosFallidosDeUsuario(usuarioQueExiste);
        Assert.assertEquals(intentosFallidos3.getCantidadIntentos(), 3);
        Assert.assertTrue(Duration.between(intentosFallidos3.getHoraDelIntentoMaximo(),horaTercerItento).getSeconds() == 0);

        // INTENTO 4: A partir de acá no debería guardar más el tiempo en la horaDeIntentoMaximo
        Thread.sleep(1000);
        criterio.errorAlLogear(usuarioQueExiste);
        LocalDateTime horaCuartoItento = LocalDateTime.now();

        IntentosFallidos intentosFallidos4 = AlmacenContrasenias.Instancia().getIntentosFallidosDeUsuario(usuarioQueExiste);
        Assert.assertEquals(intentosFallidos4.getCantidadIntentos(), 4);
        Assert.assertTrue(Duration.between(intentosFallidos4.getHoraDelIntentoMaximo(),horaCuartoItento).getSeconds() == 1);
    }

    @Test
    public void cumpleCondicionDeEspera() throws InterruptedException {
        String usuarioQueExiste = "user";

        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioQueExiste,"password");
        new CriterioLogin().validar(usuarioQueExiste,"password",new ArrayList<String>());

        IntentosFallidos intentosFallidos = AlmacenContrasenias.Instancia().getIntentosFallidosDeUsuario(usuarioQueExiste);

        criterio.validar(usuarioQueExiste,"", this.errorMessages);

        Assert.assertEquals(this.errorMessages.size(),0);
    }

    @Test
    public void cumpleCondicionDeEsperaParaUnIntentoContinuo(){
        String usuarioQueExiste = "user";

        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioQueExiste,"password");
        new CriterioLogin().validar(usuarioQueExiste,"password",new ArrayList<String>());

        // INTENTO 1
        criterio.errorAlLogear(usuarioQueExiste);
        criterio.validar(usuarioQueExiste,"",this.errorMessages);
        Assert.assertEquals(this.errorMessages.size(),0);
    }

    @Test
    public void cumpleCondicionDeEsperaParaDosIntentosContinuos(){
        String usuarioQueExiste = "user";

        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioQueExiste,"password");
        new CriterioLogin().validar(usuarioQueExiste,"password",new ArrayList<String>());

        // INTENTO 1
        criterio.errorAlLogear(usuarioQueExiste);
        criterio.validar(usuarioQueExiste,"",this.errorMessages);
        // INTENTO 2
        criterio.errorAlLogear(usuarioQueExiste);
        criterio.validar(usuarioQueExiste,"",this.errorMessages);

        Assert.assertEquals(this.errorMessages.size(),0);
    }

    @Test
    public void cumpleCondicionDeEsperaParaTresIntentosContinuos(){
        String usuarioQueExiste = "user";

        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioQueExiste,"password");
        new CriterioLogin().validar(usuarioQueExiste,"password",new ArrayList<String>());

        // INTENTO 1
        criterio.errorAlLogear(usuarioQueExiste);
        criterio.validar(usuarioQueExiste,"",this.errorMessages);
        // INTENTO 2
        criterio.errorAlLogear(usuarioQueExiste);
        criterio.validar(usuarioQueExiste,"",this.errorMessages);
        // INTENTO 3
        criterio.errorAlLogear(usuarioQueExiste);
        criterio.validar(usuarioQueExiste,"",this.errorMessages);

        Assert.assertEquals(this.errorMessages.size(),1);
    }

    @Test
    public void cumpleCondicionDeEsperaParaCuatroIntentosContinuosSinEspera(){
        String usuarioQueExiste = "user";

        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioQueExiste,"password");
        new CriterioLogin().validar(usuarioQueExiste,"password",new ArrayList<String>());

        // INTENTO 1
        criterio.errorAlLogear(usuarioQueExiste);
        criterio.validar(usuarioQueExiste,"",this.errorMessages);
        // INTENTO 2
        criterio.errorAlLogear(usuarioQueExiste);
        criterio.validar(usuarioQueExiste,"",this.errorMessages);
        // INTENTO 3
        criterio.errorAlLogear(usuarioQueExiste);
        criterio.validar(usuarioQueExiste,"",this.errorMessages);
        // INTENTO 4

        criterio.validar(usuarioQueExiste,"",this.errorMessages);

        Assert.assertEquals(this.errorMessages.size(),2);
    }

    @Test
    public void cumpleCondicionDeEsperaParaCuatroIntentosContinuosConEspera() throws InterruptedException {
        String usuarioQueExiste = "user";

        AlmacenContrasenias.Instancia().registrarContrasenia(usuarioQueExiste,"password");
        new CriterioLogin().validar(usuarioQueExiste,"password",new ArrayList<String>());

        // INTENTO 1
        criterio.errorAlLogear(usuarioQueExiste);
        criterio.validar(usuarioQueExiste,"",this.errorMessages);
        // INTENTO 2
        criterio.errorAlLogear(usuarioQueExiste);
        criterio.validar(usuarioQueExiste,"",this.errorMessages);
        // INTENTO 3
        criterio.errorAlLogear(usuarioQueExiste);
        criterio.validar(usuarioQueExiste,"",this.errorMessages);
        // INTENTO 4
        Thread.sleep(5000);

        criterio.validar(usuarioQueExiste,"",this.errorMessages);

        Assert.assertEquals(this.errorMessages.size(),1);
    }

}
