package domain.entities.seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import db.DAOs.ContraseniasPreviasDAOMemoria;
import domain.entities.seguridad.CriteriosContrasenia.CriterioRotacionContrasenia;
import db.DAOs.IntentosFallidosDAOMemoria;
import domain.entities.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class TestCriterioRotacionContrasenia {

    private CriterioRotacionContrasenia criterio;
    private List<String> errorMessages;
    private AlmacenContrasenias almacen;
    private Usuario usuario;

    @Before
    public void init(){

        this.almacen = new AlmacenContrasenias();
        this.almacen.setContraseniasPreviasDAO(new ContraseniasPreviasDAOMemoria());
        this.almacen.setIntentosFallidosDAO(new IntentosFallidosDAOMemoria());
        this.criterio = new CriterioRotacionContrasenia(this.almacen);
        this.errorMessages = new ArrayList<String>();
        this.almacen.setPeriodosDeRotacion(3);
        this.usuario = new Usuario();
        usuario.setUsuarioId("testUser");
    }

    @Test
    public void contraseniaEsNueva(){
        Usuario usuario = new Usuario();
        usuario.setUsuarioId("testUser");

        this.criterio.validar(usuario,"1234", errorMessages);
        //TODO: si algun criterio falla que no gurde la contrasenia.
        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void contraseniaEsRepetida(){
        Usuario usuario = new Usuario();
        usuario.setUsuarioId("testUser");

        this.almacen.registrarContrasenia(usuario, "1234");
        this.almacen.registrarContrasenia(usuario, "hola");

        this.criterio.validar(usuario, "1234", errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void criterioRotacionContraseniaMensajeError(){
        Usuario usuario = new Usuario();
        usuario.setUsuarioId("testUser");

        this.almacen.registrarContrasenia(usuario, "1234");
        this.almacen.registrarContrasenia(usuario, "hola");

        this.criterio.validar(usuario, "1234", errorMessages);

        Assert.assertEquals("La contrasenia repite contrasenias viejas", this.errorMessages.get(0));
    }

    @Test
    public void seBorranLasContraseniasMuyViejas(){
        this.almacen.registrarContrasenia(usuario, "1234");
        this.almacen.registrarContrasenia(usuario, "hola");
        this.almacen.registrarContrasenia(usuario, "admin");
        this.almacen.registrarContrasenia(usuario, "nombreFamiliarCercano");

        this.criterio.validar(usuario, "1234", errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void lasContraseniasNoViejasValidan(){
        this.almacen.registrarContrasenia(this.usuario, "1234");
        this.almacen.registrarContrasenia(this.usuario, "hola");
        this.almacen.registrarContrasenia(this.usuario, "admin");
        this.almacen.registrarContrasenia(this.usuario, "nombreFamiliarCercano");

        this.criterio.validar(this.usuario, "admin", errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void seDiferencianContraseniasDeDistintosUsuarios(){
        Usuario otroUsuario = new Usuario();
        otroUsuario.setUsuarioId("otroUsuario");
        this.almacen.registrarContrasenia(this.usuario, "1234");
        this.almacen.registrarContrasenia(otroUsuario, "hola");

        this.criterio.validar(otroUsuario, "1234", errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }
}
