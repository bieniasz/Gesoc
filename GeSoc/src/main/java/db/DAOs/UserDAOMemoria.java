package db.DAOs;

import db.DAOs.UserDAO;
import domain.entities.usuario.Usuario;

import java.util.HashMap;
import java.util.Map;

public class UserDAOMemoria implements UserDAO {
    private Map<String, Usuario> usuarioMap = new HashMap<>();

    public UserDAOMemoria(){

        Usuario usuario1 = new Usuario();
        usuario1.setUsuarioId("igna");
        usuario1.setContrasenia("igna");

        Usuario usuario2 = new Usuario();
        usuario1.setUsuarioId("juanpa");
        usuario1.setContrasenia("juanpa");

        this.persistirUsuario(usuario1);
        this.persistirUsuario(usuario2);
    }

    @Override
    public Usuario getUsuario(String userId) {
        return this.usuarioMap.get(userId);
    }

    @Override
    public void persistirUsuario(Usuario usuario) {
        this.usuarioMap.put(usuario.getUsuario(),usuario);
    }


}
