package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class TestCriterioMinusculasYMayusculas {

    private CriterioMinusculasYMayusculas criterio;
    private List<String> errorMessages;

    @Before
    public void init(){

        this.criterio = new CriterioMinusculasYMayusculas();
        this.errorMessages = new ArrayList<String>();
    }

    @Test
    public void contraseniaNoTieneMayusculas(){

        this.criterio.validar("testUser","nnnnnnnnnnnn", errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void contraseniaNoTieneMayusculasMensajeDeError(){

        this.criterio.validar("testUser", "nnnnnnnnnnnn", errorMessages);

        Assert.assertEquals("Faltan letras mayusculas a la contrasenia", this.errorMessages.get(0));
    }

    @Test
    public void contraseniaNoTieneMinusculas(){

        this.criterio.validar("testUser", "NNNNNN", errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void contraseniaNoTieneMinusculasMensajeDeError(){

        this.criterio.validar("testUser", "NNNNNN", errorMessages);

        Assert.assertEquals("Faltan letras minusculas a la contrasenia", this.errorMessages.get(0));
    }

    @Test
    public void contraseniaNoTieneMinuscuYMayusculasOrdenadas1(){

        this.criterio.validar("testUser", "nnnnnnNNNNNN", errorMessages);
;
        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void contraseniaNoTieneMinuscuYMayusculasOrdenadas2(){

        this.criterio.validar("testUser", "NNNnnnnn", errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void contraseniaNoTieneMinuscuYMayusculasMixeadas(){

        this.criterio.validar("testUser", "nNnNn", errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }
}
