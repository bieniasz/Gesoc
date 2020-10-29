package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.operacionComercial.OperacionIngreso;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

public class OperacionIngresoDAOMySQL implements OperacionIngresoDAO {

    @Override
    public OperacionIngreso buscarOperacionIngreso(Integer id) {
        return EntityManagerHelper.getEntityManager().find(OperacionIngreso.class, id);
    }

    @Override
    public List<OperacionIngreso> getOperacionesIngresoPorOrganizacion(Integer organizacionId) {
        List<OperacionIngreso> operaciones;
        Query query = EntityManagerHelper.createQuery("from OperacionIngreso o where o.organizacion = " + organizacionId.toString());

        try{
            operaciones = query.getResultList();
        } catch (NoResultException e){
            operaciones = null;
            e.printStackTrace();
        }
        return operaciones;
    }

    @Override
    public void guardarOperacionIngreso(OperacionIngreso ingreso) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(ingreso);
        EntityManagerHelper.commit();
    }

    @Override
    public void modificarOperacionIngreso(OperacionIngreso ingreso) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(ingreso);
        EntityManagerHelper.commit();
    }
}
