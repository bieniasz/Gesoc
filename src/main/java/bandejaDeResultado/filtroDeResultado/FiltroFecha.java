package bandejaDeResultado.filtroDeResultado;

import validadorTransparencia.ResultadoDeValidacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FiltroFecha implements FiltroDeResultado{

    private LocalDate fechaDesde;
    private LocalDate fechaHasta;

    public FiltroFecha(LocalDate filtrarDesde, LocalDate filtrarHasta){
        this.fechaDesde = filtrarDesde;
        this.fechaHasta = filtrarHasta;
    }

    @Override
    public List<ResultadoDeValidacion> filtrar(List<ResultadoDeValidacion> resultados){
        List<ResultadoDeValidacion> resultadosFiltrados;

        resultadosFiltrados = resultados.stream()
                .filter(res -> {
                    return res.fecha().isAfter(fechaDesde) && res.fecha().isBefore(fechaHasta); // creo que faltan los límites del intervalo osea los =                            ;
                })
                .collect(Collectors.toList());

        return resultadosFiltrados;
    }
    public LocalDate getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDate fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDate fechaHasta) {
        this.fechaHasta = fechaHasta;
    }


}
