package db.DAOs;

import domain.entities.usuario.Usuario;

public interface UserDAO {
    public Usuario buscarUsuarioPoruserId(String userId);
    public Usuario buscarUsuario(Integer id);
    public void guardarUsuario(Usuario usuario);
    void modificarUsuario(Usuario usuario);
}
