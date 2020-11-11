package domain.entities.vinculacionIngresoEgresos.adapters;

import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.OperacionIngreso;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IAdapterVinculacion {
    Map<OperacionEgreso, OperacionIngreso> obtenerVinculaciones(List<OperacionIngreso> operacionesIngreso, List<OperacionEgreso> operacionesEgreso, String criterio) throws IOException;
}
