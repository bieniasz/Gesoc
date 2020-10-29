package db.DAOs;

import domain.entities.operacionComercial.Presupuesto;

public interface PresupuestoDAO {
    Presupuesto buscarPresupuesto(Integer id);
    void guardarPresupuesto(Presupuesto presupuesto);
    void modificarPresupuesto(Presupuesto presupuesto);
}
