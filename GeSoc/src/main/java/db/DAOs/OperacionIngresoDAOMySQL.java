package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.operacionComercial.OperacionIngreso;

public class OperacionIngresoDAOMySQL implements OperacionIngresoDAO {

    @Override
    public OperacionIngreso buscarOperacionIngreso(Integer id) {
        return EntityManagerHelper.getEntityManager().find(OperacionIngreso.class, id);
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
