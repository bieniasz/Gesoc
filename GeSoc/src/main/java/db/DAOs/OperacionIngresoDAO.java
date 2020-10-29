package db.DAOs;

import domain.entities.operacionComercial.OperacionIngreso;

public interface OperacionIngresoDAO {
    OperacionIngreso buscarOperacionIngreso(Integer id);
    void guardarOperacionIngreso(OperacionIngreso ingreso);
    void modificarOperacionIngreso(OperacionIngreso ingreso);
}
