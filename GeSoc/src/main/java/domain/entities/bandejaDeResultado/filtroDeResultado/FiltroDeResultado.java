package domain.entities.bandejaDeResultado.filtroDeResultado;

import domain.entities.validadorTransparencia.ResultadoDeValidacion;

import java.util.List;

public interface FiltroDeResultado {

    List<ResultadoDeValidacion> filtrar(List<ResultadoDeValidacion> resultados);

}
