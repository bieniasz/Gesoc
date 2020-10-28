package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.seguridad.ContraseniasPrevias.ContraseniasPrevias;
import domain.entities.usuario.Usuario;

import javax.persistence.Query;

public class UserDAOMySQL implements UserDAO {

    @Override
    public Usuario buscarUsuarioPoruserId(String userId){
        Usuario usuario = (Usuario) EntityManagerHelper.createQuery("from Usuario u where u.usuarioId = '"+userId+"'").getSingleResult();
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

    public void actualizarUsuario(Usuario usuario){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(usuario);
        EntityManagerHelper.commit();
    }
}
