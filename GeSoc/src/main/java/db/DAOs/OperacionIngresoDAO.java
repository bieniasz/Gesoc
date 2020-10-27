package db.DAOs;

import domain.entities.operacionComercial.OperacionIngreso;

public interface OperacionIngresoDAO {
    OperacionIngreso buscarIngreso(Integer id);
    void guardarIngreso(OperacionIngreso ingreso);
    void actualizarIngreso(OperacionIngreso ingreso);
}
