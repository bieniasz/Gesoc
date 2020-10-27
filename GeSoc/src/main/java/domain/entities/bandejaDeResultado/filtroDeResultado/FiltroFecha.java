package domain.entities.bandejaDeResultado.filtroDeResultado;

import domain.entities.validadorTransparencia.ResultadoDeValidacion;

import java.time.LocalDate;
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
                .filter(res -> this.estaDentroDelIntervaloDesdeHasta(res.getFecha()))
                .collect(Collectors.toList());

        return resultadosFiltrados;
    }

    private Boolean estaDentroDelIntervaloDesdeHasta(LocalDate fecha){
        return (fecha.isEqual(fechaDesde) || fecha.isAfter(fechaDesde)) &&
                (fecha.isEqual(fechaHasta) || fecha.isBefore(fechaHasta));
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
