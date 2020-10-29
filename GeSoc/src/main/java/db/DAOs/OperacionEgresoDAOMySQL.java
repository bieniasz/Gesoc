package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.operacionComercial.OperacionEgreso;

public class OperacionEgresoDAOMySQL implements OperacionEgresoDAO {
    @Override
    public OperacionEgreso buscarOperacionEgresoPorId(Integer id) {
        return EntityManagerHelper.getEntityManager().find(OperacionEgreso.class, id);
    }

    @Override
    public OperacionEgreso buscarEgreso(Integer id) {
        return null;
    }

    @Override
    public void guardarOperacionEgreso(OperacionEgreso egreso) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(egreso);
        EntityManagerHelper.commit();
    }

    public void actualizarOperacionEgreso(OperacionEgreso egreso){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(egreso);
        EntityManagerHelper.commit();
    }
}