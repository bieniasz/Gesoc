package seguridad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import usuario.UsuarioAdmin;
import usuario.UsuarioEstandar;
import usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class TestCriterioListaNegra {

    private CriterioFueraListaNegra criterio;
    private List<String> errorMessages;
    private UsuarioAdmin usuarioAdmin = new UsuarioAdmin("admin", "admin123" );

    @Before
    public void init(){

        this.criterio = new CriterioFueraListaNegra();
        this.errorMessages = new ArrayList<String>();
    }

    @Test
    public void contraseniaEnListaNegra(){

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar("testUser", "123456");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void contraseniaNOestaEnListaNegra(){

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar("testUser", "ContrS3ni$°Segur1s1m4");
        this.criterio.validar(usuario, errorMessages);;

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void criterioListaNegraMensajeDeError(){

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar("testUser", "123456");
        this.criterio.validar(usuario, errorMessages);;

        Assert.assertEquals("Contrasenia pertenece a lista negra", this.errorMessages.get(0));
    }
}
