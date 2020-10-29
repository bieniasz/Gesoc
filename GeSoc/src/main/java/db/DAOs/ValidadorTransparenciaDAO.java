package db.DAOs;

import domain.entities.validadorTransparencia.ResultadoDeValidacion;

public interface ValidadorTransparenciaDAO {
    void guardarResultadoDeValidacion(ResultadoDeValidacion resultado);
    ResultadoDeValidacion buscarResultado(Integer id);
    void modificarResultadoDeValidacion(ResultadoDeValidacion resultadoDeValidacion);
}
