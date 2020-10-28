package db.DAOs;

import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.OperacionIngreso;

public interface OperacionEgresoDAO {
    OperacionEgreso buscarEgreso(Integer id);
    void guardarOperacionEgreso(OperacionEgreso ingreso);
}
