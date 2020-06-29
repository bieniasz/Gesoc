package bandejaDeResultado.filtroDeResultado;

import validadorTransparencia.ResultadoDeValidacion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FiltroLeido implements FiltroDeResultado{

    @Override
    public List<ResultadoDeValidacion> filtrar(List<ResultadoDeValidacion> resultados){
        List<ResultadoDeValidacion> resultadosFiltrados;

        resultadosFiltrados = resultados.stream()
                .filter(ResultadoDeValidacion::leido)
                .collect(Collectors.toList());

        return resultadosFiltrados;
    }
}
