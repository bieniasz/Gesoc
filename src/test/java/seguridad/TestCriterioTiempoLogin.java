package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
        public void tardaMenosDeTresSegundos () throws InterruptedException {

            Usuario usuario = new Usuario("testUser", "NNNNNN");
            this.criterio.validar(usuario, errorMessages);
            Thread.sleep(1000);
            this.criterio.validar(usuario, errorMessages);

            Assert.assertEquals("No han pasado 3 segundos entre los intentos", this.errorMessages.get(0));


    }
}