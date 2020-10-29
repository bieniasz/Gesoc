package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.organizacion.Organizacion;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class OperacionEgresoDAOMySQL implements OperacionEgresoDAO {
    @Override
    public OperacionEgreso buscarOperacionEgresoPorId(Integer id) {
        return EntityManagerHelper.getEntityManager().find(OperacionEgreso.class, id);
    }

    @Override
    public void modificarOperacionEgreso(OperacionEgreso egreso) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(egreso);
        EntityManagerHelper.commit();
    }

    @Override
    public void guardarOperacionEgreso(OperacionEgreso egreso) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(egreso);
        EntityManagerHelper.commit();
    }

    public List<OperacionEgreso> getOperacionesEgresoPorOrganizacion(Integer organizacionID) {
        List<OperacionEgreso> operaciones;
        Query query = EntityManagerHelper.createQuery("from OperacionEgreso o where o.organizacion = " + organizacionID.toString());

        try{
            operaciones = query.getResultList();
        } catch (NoResultException e){
            operaciones = null;
            e.printStackTrace();
        }
        return operaciones;
    }
}