package db.DAOs;

import db.EntityManagerHelper;
import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.OperacionIngreso;

public class OperacionEgresoDAOMemoria implements OperacionEgresoDAO {
    @Override
    public OperacionEgreso buscarEgreso(Integer id) {
        return EntityManagerHelper.getEntityManager().find(OperacionEgreso.class, id);
    }

    @Override
    public void guardarOperacionEgreso(OperacionEgreso egreso) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(egreso.getOrganizacion());
        EntityManagerHelper.getEntityManager().persist(egreso);
        EntityManagerHelper.commit();
    }
}
