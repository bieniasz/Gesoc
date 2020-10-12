package domain.entities.bandejaDeResultado.filtroDeResultado;

import domain.entities.validadorTransparencia.ResultadoDeValidacion;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroLeido implements FiltroDeResultado{

    @Override
    public List<ResultadoDeValidacion> filtrar(List<ResultadoDeValidacion> resultados){
        List<ResultadoDeValidacion> resultadosFiltrados;

        resultadosFiltrados = resultados.stream()
                .filter(res -> res.leido())
                .collect(Collectors.toList());

        return resultadosFiltrados;
    }
}
