package domain.entities.vinculacionIngresoEgresos;

import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.OperacionIngreso;
import domain.entities.vinculacionIngresoEgresos.adapters.IAdapterVinculacion;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class VinculadorIngresoEgresos {
    private List<OperacionIngreso> operacionIngresoList;
    private List<OperacionEgreso> operacionEgresoList;
    private LocalDate fechaHastaAceptable;
    private IAdapterVinculacion adapterVinculador;

    public void vincularOperaciones() throws IOException {
        Map<OperacionEgreso, OperacionIngreso> mapIngresosEgresos = this.adapterVinculador.obtenerEgresosVinculados(operacionIngresoList, operacionEgresoList, fechaHastaAceptable);

        for (OperacionEgreso egreso : mapIngresosEgresos.keySet()) {
            OperacionIngreso ingreso = mapIngresosEgresos.get(egreso);
            egreso.setIngresoAsociado(ingreso);
            //TODO PERSISTIR
        }
    }

    public List<OperacionIngreso> getOperacionIngreso() {
        return operacionIngresoList;
    }
    public List<OperacionEgreso> getOperacionEgresoList() {
        return operacionEgresoList;
    }
    public LocalDate getFechaHastaAceptable() { return fechaHastaAceptable; }
    public IAdapterVinculacion getAdapterVinculador() {
        return adapterVinculador;
    }

    public void setOperacionIngreso(List<OperacionIngreso> operacionIngreso) { this.operacionIngresoList = operacionIngreso; }
    public void setOperacionEgresoList(List<OperacionEgreso> operacionEgresoList) { this.operacionEgresoList = operacionEgresoList;    }
    public void setFechaHastaAceptable(LocalDate fechaHastaAceptable) { this.fechaHastaAceptable = fechaHastaAceptable; }
    public void setAdapterVinculador(IAdapterVinculacion adapter){
        this.adapterVinculador = adapter;
    }

    public void addOperacionIngreso(OperacionIngreso operacionIngreso){this.operacionIngresoList.add(operacionIngreso); }
    public void addOperacionEgreso(OperacionEgreso operacionEgreso){
        this.operacionEgresoList.add(operacionEgreso);
    }
}
