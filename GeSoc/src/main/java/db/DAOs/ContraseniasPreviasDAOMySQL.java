package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.seguridad.ContraseniasPrevias.ContraseniasPrevias;
import domain.entities.usuario.Usuario;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


public class ContraseniasPreviasDAOMySQL implements ContraseniasPreviasDAO {


    @Override
    public ContraseniasPrevias getContraseniasPrevias(Usuario usuario) {
        EntityManagerHelper.beginTransaction();
        Query query = EntityManagerHelper.createQuery("FROM ContraseniasPrevias cp WHERE cp.usuarioId = :user");
        query.setParameter("user", usuario.getId());
        ContraseniasPrevias containerContraseniasPrevias = (ContraseniasPrevias) query.getSingleResult();
        EntityManagerHelper.closeEntityManager();
        return containerContraseniasPrevias != null ? containerContraseniasPrevias : new ContraseniasPrevias();
    }

    @Override
    public void persistirContraseniaPrevia(ContraseniasPrevias contraseniasPrevias) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(contraseniasPrevias);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }

    public void actualizarContraseniasPrevias(ContraseniasPrevias contraseniasPrevias){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(contraseniasPrevias);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }

    public ContraseniasPrevias getContraseniasPrevias(Integer usuarioId) {
        EntityManagerHelper.beginTransaction();
        Query query = EntityManagerHelper.createQuery("FROM ContraseniasPrevias cp WHERE cp.usuarioId = :user");
        query.setParameter("user", usuarioId);
        ContraseniasPrevias containerContraseniasPrevias = (ContraseniasPrevias) query.getSingleResult();
        EntityManagerHelper.closeEntityManager();
        return containerContraseniasPrevias != null ? containerContraseniasPrevias : new ContraseniasPrevias();
    }
}
