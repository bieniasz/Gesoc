package seguridad.IntentosFallidos;

import usuario.Usuario;

import java.util.HashMap;
import java.util.Map;

public class IntentosFallidosDAOMemoria implements IntentosFallidosDAO{

    private Map<Usuario, IntentosFallidos> intentos = new HashMap<>();
    private Usuario usuarioEnUso;

    @Override
    public IntentosFallidos getIntentosFallidos(Usuario usuario) {
        this.usuarioEnUso = usuario;
        return this.intentos.get(usuario);
    }

    @Override
    public void persistirIntentoFallido(IntentosFallidos intentoFallido) {
        this.intentos.put(usuarioEnUso, intentoFallido);
    }
}
