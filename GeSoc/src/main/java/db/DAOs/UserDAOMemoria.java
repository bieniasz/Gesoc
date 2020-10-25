package db.DAOs;

import db.DAOs.UserDAO;
import domain.entities.usuario.Usuario;

import java.util.HashMap;
import java.util.Map;

public class UserDAOMemoria implements UserDAO {
    private Map<String, Usuario> usuarioMap = new HashMap<>();

    @Override
    public Usuario buscarUsuarioPoruserId(String userId) {
        return this.usuarioMap.get(userId);
    }

    @Override
    public Usuario buscarUsuario(Integer id){return null;};

    @Override
    public void guardarUsuario(Usuario usuario) {
        this.usuarioMap.put(usuario.getUsuario(),usuario);
    }
}
