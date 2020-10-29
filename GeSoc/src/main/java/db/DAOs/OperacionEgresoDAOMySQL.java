package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.operacionComercial.OperacionEgreso;

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
}