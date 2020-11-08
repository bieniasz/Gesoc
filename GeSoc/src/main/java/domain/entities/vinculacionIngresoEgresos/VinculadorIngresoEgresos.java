package domain.entities.vinculacionIngresoEgresos;

import db.DAOs.OperacionEgresoDAO;
import db.DAOs.OperacionEgresoDAOMemoria;
import db.DAOs.OperacionIngresoDAO;
import db.DAOs.OperacionIngresoDAOMySQL;
import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.OperacionIngreso;
import domain.entities.vinculacionIngresoEgresos.adapters.IAdapterVinculacion;
import domain.entities.vinculacionIngresoEgresos.adapters.adapterVinculator.AdapterVinculator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class VinculadorIngresoEgresos {
    private List<OperacionIngreso> operacionIngresoList;
    private List<OperacionEgreso> operacionEgresoList;
    private IAdapterVinculacion adapterVinculador;

    public VinculadorIngresoEgresos(){ this.adapterVinculador = new AdapterVinculator(); }

    public void vincularOperaciones() throws IOException {
        OperacionEgresoDAO egresoDAO = new OperacionEgresoDAOMemoria();
        OperacionIngresoDAO ingresoDAO = new OperacionIngresoDAOMySQL();

        Map<OperacionEgreso, OperacionIngreso> mapIngresosEgresos = this.adapterVinculador.obtenerVinculaciones(this.operacionIngresoList, this.operacionEgresoList);

        for (OperacionEgreso egreso : mapIngresosEgresos.keySet()) {
            OperacionIngreso ingreso = mapIngresosEgresos.get(egreso);
            egreso.setIngresoAsociado(ingreso);

            egresoDAO.guardarOperacionEgreso(egreso);
            ingresoDAO.guardarOperacionIngreso(ingreso);
        }
    }

    public List<OperacionIngreso> getOperacionIngresoList() {
        return operacionIngresoList;
    }
    public List<OperacionEgreso> getOperacionEgresoList() {
        return operacionEgresoList;
    }

    public void setOperacionIngresoList(List<OperacionIngreso> operacionIngreso) { this.operacionIngresoList = operacionIngreso; }
    public void setOperacionEgresoList(List<OperacionEgreso> operacionEgresoList) { this.operacionEgresoList = operacionEgresoList;    }
    public void setAdapterVinculador(IAdapterVinculacion adapter){
        this.adapterVinculador = adapter;
    }

    public void addOperacionIngreso(OperacionIngreso operacionIngreso){this.operacionIngresoList.add(operacionIngreso); }
    public void addOperacionEgreso(OperacionEgreso operacionEgreso){
        this.operacionEgresoList.add(operacionEgreso);
    }
}
