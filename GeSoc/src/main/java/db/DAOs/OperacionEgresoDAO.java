package db.DAOs;

import domain.entities.operacionComercial.OperacionEgreso;

import java.util.List;

public interface OperacionEgresoDAO {

    void guardarOperacionEgreso(OperacionEgreso egreso);
    OperacionEgreso buscarOperacionEgresoPorId(Integer id) throws Exception;
    void modificarOperacionEgreso(OperacionEgreso egreso);
    List<OperacionEgreso> getOperacionesEgresoPorOrganizacion(Integer organizacionId);
}
