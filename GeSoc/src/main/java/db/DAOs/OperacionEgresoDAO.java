package db.DAOs;

import domain.entities.operacionComercial.OperacionEgreso;

public interface OperacionEgresoDAO {

    void guardarOperacionEgreso(OperacionEgreso operacion);
    OperacionEgreso buscarOperacionEgresoPorId(Integer id) throws Exception;
}
