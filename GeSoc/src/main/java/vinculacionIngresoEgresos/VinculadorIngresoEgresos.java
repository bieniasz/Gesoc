package vinculacionIngresoEgresos;

import ProveedorDocComer.DocumentoComercial;
import ProveedorDocComer.Proveedor;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import operacionComercial.DetalleEgreso;
import operacionComercial.MedioDePago;
import operacionComercial.OperacionEgreso;
import operacionComercial.OperacionIngreso;
import org.json.JSONArray;
import org.json.JSONObject;
import organizacion.EntidadJuridica;
import organizacion.categoria.Empresa;
import vinculacionIngresoEgresos.adapters.IAdapterVinculacion;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VinculadorIngresoEgresos {
    private List<OperacionIngreso> operacionIngresoList;
    private List<OperacionEgreso> operacionEgresoList;
    private IAdapterVinculacion adapterVinculador;

    public void vincularOperaciones() throws IOException {
        Map<OperacionEgreso, OperacionIngreso> mapIngresosEgresos = this.adapterVinculador.obtenerEgresosVinculados(operacionIngresoList, operacionEgresoList);

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
    public IAdapterVinculacion getAdapterVinculador() {
        return adapterVinculador;
    }

    public void setOperacionIngreso(List<OperacionIngreso> operacionIngreso) { this.operacionIngresoList = operacionIngreso; }
    public void setOperacionEgresoList(List<OperacionEgreso> operacionEgresoList) { this.operacionEgresoList = operacionEgresoList;    }
    public void setAdapterVinculador(IAdapterVinculacion adapter){
        this.adapterVinculador = adapter;
    }

    public void addOperacionIngreso(OperacionIngreso operacionIngreso){
        this.operacionIngresoList.add(operacionIngreso);
    }
    public void addOperacionEgreso(OperacionEgreso operacionEgreso){
        this.operacionEgresoList.add(operacionEgreso);
    }
}
