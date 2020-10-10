package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class TestCriterioLogin {

    private CriterioLogin criterio;
    private List<String> errorMessages;
    private AlmacenContrasenias almacen;

    @Before
    public void init() {
        //TODO: setear DAOs dummy
        this.almacen = new AlmacenContrasenias();
        this.criterio = new CriterioLogin(this.almacen);
        this.errorMessages = new ArrayList<String>();
    }

    @Test
    public void usuarioYcontraseniaCoincidenConElALmacen(){
        //TODO: tengo que asegurarme solo con la contrasenia actual y no una vieja
        Usuario user = new Usuario();
        user.setUsuarioId("user");
        this.almacen.registrarContrasenia(user,"password");
        this.criterio.validar(user,"password",errorMessages);

        Assert.assertEquals(0,errorMessages.size());
    }

    @Test
    public void usuarioNoCoincideConElALmacen(){
        Usuario user = new Usuario();
        user.setUsuarioId("user");

        Usuario user2 = new Usuario();
        user.setUsuarioId("otroUser");

        this.almacen.registrarContrasenia(user,"password");
        this.almacen.registrarContrasenia(user2,"password2");

        this.criterio.validar(user2,"password",errorMessages);

        Assert.assertEquals(1,errorMessages.size());
    }

    @Test
    public void contraseniaNoCoincideConElALmacen(){
        Usuario user = new Usuario();
        user.setUsuarioId("user");
        this.almacen.registrarContrasenia(user,"password");
        this.criterio.validar(user,"passwordInconrrecta",errorMessages);

        Assert.assertEquals(1,errorMessages.size());
    }

    @Test
    public void usuarioYContraseniaNoCoincideConElALmacen(){
        Usuario user = new Usuario();
        user.setUsuarioId("user");

        Usuario user2 = new Usuario();
        user.setUsuarioId("user2");
        this.almacen.registrarContrasenia(user,"password");
        this.criterio.validar(user2, "passwordincorrecta",errorMessages);

        Assert.assertEquals(1,errorMessages.size());
    }

}
