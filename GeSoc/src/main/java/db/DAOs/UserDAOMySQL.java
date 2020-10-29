package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.usuario.Usuario;

import javax.persistence.NoResultException;
import java.util.Arrays;

public class UserDAOMySQL implements UserDAO {

    @Override
    public Usuario buscarUsuarioPoruserId(String userId){
        Usuario usuario;
        try{
            usuario = (Usuario) EntityManagerHelper
                    .createQuery("from Usuario u where u.usuarioId = '" + userId + "'")
                    .getSingleResult();
        } catch (NoResultException e){
            usuario = null;
            System.out.println(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
        return usuario;
    }

    @Override
    public Usuario buscarUsuario(Integer id) {
        return EntityManagerHelper.getEntityManager().find(Usuario.class, id);
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(usuario);
        EntityManagerHelper.commit();
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(usuario);
        EntityManagerHelper.commit();
    }
}
