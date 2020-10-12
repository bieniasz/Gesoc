package domain.entities.usuario;

public interface UserDAO {
    public Usuario getUsuario(String userId);
    public void persistirUsuario(Usuario usuario);
}
