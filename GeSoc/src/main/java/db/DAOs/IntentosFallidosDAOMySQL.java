package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.seguridad.IntentosFallidos.IntentosFallidos;
import domain.entities.usuario.Usuario;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class IntentosFallidosDAOMySQL implements IntentosFallidosDAO {
    @Override
    public IntentosFallidos getIntentosFallidos(Usuario usuario) {
        IntentosFallidos intentosFallidos;
        Query query = EntityManagerHelper.createQuery("from IntentosFallidos i where i.usuarioId = :user");
        query.setParameter("user", usuario.getId());
        try {
            intentosFallidos = (IntentosFallidos) query.getSingleResult();
        } catch (NoResultException e){
            intentosFallidos = null;
        }
        return intentosFallidos;
    }

    @Override
    public void persistirIntentoFallido(IntentosFallidos intentoFallido) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(intentoFallido);
        EntityManagerHelper.commit();
    }

    @Override
    public void actualizarIntentoFallido(IntentosFallidos intentosFallidos) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(intentosFallidos);
        EntityManagerHelper.commit();
    }
}
