package db.DAOs;

import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.OperacionIngreso;

public interface OperacionEgresoDAO {

    void guardarOperacionEgreso(OperacionEgreso operacion);

    OperacionEgreso buscarOperacionEgresoPorId(Integer id) throws Exception;

}
