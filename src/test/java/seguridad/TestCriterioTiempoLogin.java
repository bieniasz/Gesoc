package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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

        this.criterio.validar("testUser","NNNNNN" , errorMessages);
        Thread.sleep(1000);
        this.criterio.validar("testUser","NNNNNN" , errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void tardaMasDeTresSegundos () throws InterruptedException {

        this.criterio.validar("testUser","NNNNNN" , errorMessages);
        Thread.sleep(4000);
        this.criterio.validar("testUser","NNNNNN" , errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
     public void tardaMenosDeTresSegundosMensajeDeError () throws InterruptedException {

         this.criterio.validar("testUser","NNNNNN" , errorMessages);
         Thread.sleep(1000);
         this.criterio.validar("testUser", "NNNNNN", errorMessages);

         Assert.assertEquals("Debe esperar mas de tres segundos para volver a intentar", this.errorMessages.get(0));
    }
}