package db.DAOs;

import domain.entities.operacionComercial.OperacionIngreso;

import java.util.List;

public interface OperacionIngresoDAO {
    OperacionIngreso buscarOperacionIngreso(Integer id);
    List<OperacionIngreso> getOperacionesIngresoPorOrganizacion(Integer organizacionId);
    void guardarOperacionIngreso(OperacionIngreso ingreso);
    void modificarOperacionIngreso(OperacionIngreso ingreso);
}
