package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
        this.criterio.validar("nnnnnnnnnnnn", this.errorMessages);
        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void contraseniaNoTieneMayusculasMensajeDeError(){
        this.criterio.validar("nnnnnnnnnnnn", this.errorMessages);
        Assert.assertEquals("Faltan letras mayusculas a la contrasenia", this.errorMessages.get(0));
    }

    @Test
    public void contraseniaNoTieneMinusculas(){
        this.criterio.validar("NNNNNN", this.errorMessages);
        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void contraseniaNoTieneMinusculasMensajeDeError(){
        this.criterio.validar("NNNNNN", this.errorMessages);
        Assert.assertEquals("Faltan letras minusculas a la contrasenia", this.errorMessages.get(0));
    }

    @Test
    public void contraseniaNoTieneMinuscuYMayusculasOrdenadas1(){
        this.criterio.validar("nnnnnnNNNNNN", this.errorMessages);
        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void contraseniaNoTieneMinuscuYMayusculasOrdenadas2(){
        this.criterio.validar("NNNnnnnn", this.errorMessages);
        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void contraseniaNoTieneMinuscuYMayusculasMixeadas(){
        this.criterio.validar("nNnNn", this.errorMessages);
        Assert.assertEquals(0, this.errorMessages.size());
    }
}
