package domain.entities.seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import domain.entities.seguridad.CriteriosContrasenia.CriterioMinusculasYMayusculas;
import domain.entities.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class TestCriterioMinusculasYMayusculas {

    private CriterioMinusculasYMayusculas criterio;
    private List<String> errorMessages;
    private Usuario user;

    @Before
    public void init(){

        this.criterio = new CriterioMinusculasYMayusculas();
        this.errorMessages = new ArrayList<String>();
        this.user = new Usuario();
    }

    @Test
    public void contraseniaNoTieneMayusculas(){

        this.criterio.validar(this.user,"nnnnnnnnnnnn", errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void contraseniaNoTieneMayusculasMensajeDeError(){

        this.criterio.validar(this.user, "nnnnnnnnnnnn", errorMessages);

        Assert.assertEquals("Faltan letras mayusculas a la contrasenia", this.errorMessages.get(0));
    }

    @Test
    public void contraseniaNoTieneMinusculas(){

        this.criterio.validar(this.user, "NNNNNN", errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void contraseniaNoTieneMinusculasMensajeDeError(){

        this.criterio.validar(this.user, "NNNNNN", errorMessages);

        Assert.assertEquals("Faltan letras minusculas a la contrasenia", this.errorMessages.get(0));
    }

    @Test
    public void contraseniaNoTieneMinuscuYMayusculasOrdenadas1(){

        this.criterio.validar(this.user, "nnnnnnNNNNNN", errorMessages);
;
        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void contraseniaNoTieneMinuscuYMayusculasOrdenadas2(){

        this.criterio.validar(this.user, "NNNnnnnn", errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void contraseniaNoTieneMinuscuYMayusculasMixeadas(){

        this.criterio.validar(this.user, "nNnNn", errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }
}
