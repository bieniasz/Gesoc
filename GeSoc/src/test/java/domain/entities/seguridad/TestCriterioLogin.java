package domain.entities.seguridad;

import db.DAOs.IntentosFallidosDAOMemoria;
import db.DAOs.IntentosFallidosDAOMySQL;
import domain.entities.seguridad.excepciones.UsuarioContraseniaInvalidosException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import db.DAOs.ContraseniasPreviasDAOMemoria;
import domain.entities.seguridad.CriteriosLogin.CriterioLogin;
import domain.entities.usuario.Usuario;

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
        this.almacen.setContraseniasPreviasDAO(new ContraseniasPreviasDAOMemoria());
        this.almacen.setIntentosFallidosDAO(new IntentosFallidosDAOMemoria());
        this.criterio = new CriterioLogin(this.almacen);
        this.errorMessages = new ArrayList<String>();
    }

    @Test
    public void usuarioYcontraseniaCoincidenConElALmacen() throws UsuarioContraseniaInvalidosException {
        //TODO: tengo que asegurarme solo con la contrasenia actual y no una vieja
        //TODO: debugear para ver que no se me este pasando nada
        Usuario user = new Usuario();
        user.setUsuarioId("user");
        user.setContrasenia("password");
        this.almacen.registrarContrasenia(user,"password");
        this.criterio.validar(user,"password",errorMessages);

        Assert.assertEquals(0,errorMessages.size());
    }

    @Test (expected = UsuarioContraseniaInvalidosException.class)
    public void usuarioNoCoincideConElALmacen() throws UsuarioContraseniaInvalidosException {
        Usuario user = new Usuario();
        user.setUsuarioId("user");
        user.setContrasenia("password");
        Usuario user2 = new Usuario();
        user2.setUsuarioId("otroUser");
        user2.setContrasenia("password2");

        this.almacen.registrarContrasenia(user,"password");
        this.almacen.registrarContrasenia(user2,"password2");

        this.criterio.validar(user2,"password",errorMessages);
    }

    @Test (expected = UsuarioContraseniaInvalidosException.class)
    public void contraseniaNoCoincideConElALmacen() throws UsuarioContraseniaInvalidosException {
        Usuario user = new Usuario();
        user.setUsuarioId("user");
        user.setContrasenia("password");
        this.criterio.validar(user,"passwordInconrrecta",errorMessages);
    }
}
