package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import usuario.Estandar;
import usuario.Usuario;

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
    }

    @Test
    public void tardaMenosDeTresSegundos() throws InterruptedException {

        Usuario usuario = new Estandar("testUser", "NNNNNN");
        this.criterio.validar(usuario, errorMessages);
        Thread.sleep(1000);
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void tardaMasDeTresSegundos () throws InterruptedException {

        Usuario usuario = new Estandar("testUser", "NNNNNN");
        this.criterio.validar(usuario, errorMessages);
        Thread.sleep(4000);
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
     public void tardaMenosDeTresSegundosMensajeDeError () throws InterruptedException {

         Usuario usuario = new Estandar("testUser", "NNNNNN");
         this.criterio.validar(usuario, errorMessages);
         Thread.sleep(1000);
         this.criterio.validar(usuario, errorMessages);

         Assert.assertEquals("Debe esperar mas de tres segundos para volver a intentar", this.errorMessages.get(0));
    }
}