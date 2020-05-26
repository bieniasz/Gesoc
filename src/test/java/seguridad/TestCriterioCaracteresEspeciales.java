package seguridad;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import usuario.UsuarioAdmin;
import usuario.UsuarioEstandar;
import usuario.Usuario;

import java.util.ArrayList;
import java.util.List;


public class TestCriterioCaracteresEspeciales {

    private CriterioCaracteresEspeciales criterio;
    private List<String> errorMessages;
    private final String nombreUsuario = "testUser";
    private UsuarioAdmin usuarioAdmin = new UsuarioAdmin("admin", "admin123" );

    @Before
    public void init(){

        this.criterio = new CriterioCaracteresEspeciales();
        this.errorMessages = new ArrayList<String>();
    }

    @Test
    public void caracteresEspecialesAlFinal(){

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar(nombreUsuario, "hola!$");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void caracteresEspecialesEnMedio(){

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar(nombreUsuario, "hol!$a");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void caracteresEspecialesAlPrincipio(){

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar(nombreUsuario, "!$hola");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(0, this.errorMessages.size());
    }

    @Test
    public void contraseniaSinCaracteresEspeciales(){

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar(nombreUsuario, "hola");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals(1, this.errorMessages.size());
    }

    @Test
    public void mensajeContraseniaSinCaracteresEspeciales(){

        Usuario usuario = usuarioAdmin.nuevoUsuarioEstandar(nombreUsuario, "hola");
        this.criterio.validar(usuario, errorMessages);

        Assert.assertEquals("Faltan caracteres especiales", this.errorMessages.get(0));
    }
}
