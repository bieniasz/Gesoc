package db.DAOs;

import java.util.List;

import domain.entities.operacionComercial.Presupuesto;

public interface PresupuestoDAO {
    Presupuesto buscarPresupuesto(Integer id);
    void guardarPresupuesto(Presupuesto presupuesto);
    void modificarPresupuesto(Presupuesto presupuesto);
	List<Presupuesto> buscarPresupuestoEgresoId(Integer idEgreso);
}
