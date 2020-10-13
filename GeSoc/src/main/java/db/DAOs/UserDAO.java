package db.DAOs;

import domain.entities.usuario.Usuario;

public interface UserDAO {
    public Usuario getUsuario(String userId);
    public void persistirUsuario(Usuario usuario);
}
