package usuario;

import java.util.HashMap;
import java.util.Map;

public class UserDAOMemoria implements UserDAO{
    private Map<String, Usuario> usuarioMap = new HashMap<>();

    @Override
    public Usuario getUsuario(String userId) {
        return this.usuarioMap.get(userId);
    }

    @Override
    public void persistirUsuario(Usuario usuario) {
        this.usuarioMap.put(usuario.getUsuario(),usuario);
    }
}
