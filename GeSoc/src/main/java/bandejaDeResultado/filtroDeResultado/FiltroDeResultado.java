package bandejaDeResultado.filtroDeResultado;

import validadorTransparencia.ResultadoDeValidacion;

import java.util.List;

public interface FiltroDeResultado {

    List<ResultadoDeValidacion> filtrar(List<ResultadoDeValidacion> resultados);

}
