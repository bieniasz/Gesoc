package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestCriterioLogin {

    private CriterioLogin criterio;
    private List<String> errorMessages;

    @Before
    public void init() {

        this.criterio = new CriterioLogin();
        this.errorMessages = new ArrayList<String>();
        AlmacenContrasenias.Instancia().eliminarContraseniasAlmacenadas();
    }

    @Test
    public void usuarioYcontraseniaCoincidenConElALmacen(){
        AlmacenContrasenias.Instancia().registrarContrasenia("user","password");
        this.criterio.validar("user","password",errorMessages);

        Assert.assertEquals(0,errorMessages.size());
    }

    @Test
    public void usuarioNoCoincideConElALmacen(){
        AlmacenContrasenias.Instancia().registrarContrasenia("user","password");
        this.criterio.validar("usuarioNoExiste","password",errorMessages);

        Assert.assertEquals(1,errorMessages.size());
    }

    @Test
    public void contraseniaNoCoincideConElALmacen(){
        AlmacenContrasenias.Instancia().registrarContrasenia("user","password");
        this.criterio.validar("user","passwordInconrrecta",errorMessages);

        Assert.assertEquals(1,errorMessages.size());
    }

    @Test
    public void usuarioYContraseniaNoCoincideConElALmacen(){
        AlmacenContrasenias.Instancia().registrarContrasenia("user","password");
        this.criterio.validar("usuarioNoExiste","passwordincorrecta",errorMessages);

        Assert.assertEquals(1,errorMessages.size());
    }

}
