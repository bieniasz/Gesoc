package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.seguridad.ContraseniasPrevias.ContraseniasPrevias;
import domain.entities.usuario.Usuario;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


public class ContraseniasPreviasDAOMySQL implements ContraseniasPreviasDAO {


    @Override
    public ContraseniasPrevias getContraseniasPrevias(Usuario usuario) {
        ContraseniasPrevias containerContraseniasPrevias;
        Query query = EntityManagerHelper.createQuery("FROM ContraseniasPrevias cp WHERE cp.usuarioId = :user");
        query.setParameter("user", usuario.getId());
        try{
            containerContraseniasPrevias = (ContraseniasPrevias) query.getSingleResult();
        } catch (NoResultException e){
            containerContraseniasPrevias = null;
        }
        return containerContraseniasPrevias;
    }

    @Override
    public void persistirContraseniaPrevia(ContraseniasPrevias contraseniasPrevias) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(contraseniasPrevias);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }

    public void modificarContraseniasPrevias(ContraseniasPrevias contraseniasPrevias){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(contraseniasPrevias);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }
}
