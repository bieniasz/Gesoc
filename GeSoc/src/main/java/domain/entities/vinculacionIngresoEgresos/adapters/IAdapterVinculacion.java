package domain.entities.vinculacionIngresoEgresos.adapters;

import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.OperacionIngreso;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IAdapterVinculacion {
    Map<OperacionEgreso, OperacionIngreso> obtenerVinculaciones(List<OperacionIngreso> operacionesIngreso, List<OperacionEgreso> operacionesEgreso, LocalDate fechaHastaAceptable) throws IOException;
}
