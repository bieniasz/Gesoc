package vinculacionIngresoEgresos.adapters;

import operacionComercial.OperacionEgreso;
import operacionComercial.OperacionIngreso;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IAdapterVinculacion {
    Map<OperacionEgreso, OperacionIngreso> obtenerEgresosVinculados(List<OperacionIngreso> operacionesIngreso, List<OperacionEgreso> operacionesEgreso) throws IOException;
}
