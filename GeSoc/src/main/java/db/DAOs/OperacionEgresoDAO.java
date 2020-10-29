package db.DAOs;

import domain.entities.operacionComercial.OperacionEgreso;

public interface OperacionEgresoDAO {

    void guardarOperacionEgreso(OperacionEgreso egreso);
    OperacionEgreso buscarOperacionEgresoPorId(Integer id) throws Exception;
    void modificarOperacionEgreso(OperacionEgreso egreso);
}
