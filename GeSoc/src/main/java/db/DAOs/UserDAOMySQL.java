package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.usuario.Usuario;

public class UserDAOMySQL implements UserDAO {

    @Override
    public Usuario buscarUsuarioPoruserId(String userId){return null;}

    @Override
    public Usuario buscarUsuario(Integer id) {
        return EntityManagerHelper.getEntityManager().find(Usuario.class, id);
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(usuario.getRol());
        EntityManagerHelper.getEntityManager().persist(usuario);
        EntityManagerHelper.commit();
    }
}
