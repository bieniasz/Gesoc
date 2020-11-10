package db.DAOs;

import java.util.List;

import javax.persistence.NoResultException;

import db.EntityManagerHelper;
import domain.entities.operacionComercial.Presupuesto;
import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.operacionComercial.OperacionComercial;

public class PresupuestoDAOMySQL implements PresupuestoDAO {

    @Override
    public Presupuesto buscarPresupuesto(Integer id) {
        return EntityManagerHelper.getEntityManager().find(Presupuesto.class, id);
    }

    @Override
    public void guardarPresupuesto(Presupuesto presupuesto) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(presupuesto);
        EntityManagerHelper.commit();
    }

    @Override
    public void modificarPresupuesto(Presupuesto presupuesto) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(presupuesto);
        EntityManagerHelper.commit();
    }
    
      
   
    
    @Override
    public  List<Presupuesto> buscarPresupuestoEgresoId(Integer id) {
    	
    	List<Presupuesto> PresupuestoList;
    	    try{
    	    	PresupuestoList = (List<Presupuesto>) EntityManagerHelper
    	                .createQuery("from Presupuesto where egreso = "+id)
    	                .getResultList();
    	    } catch (NoResultException e){
    	    	PresupuestoList = null;
    	    }
    	    return PresupuestoList;
    }
    
    
}
