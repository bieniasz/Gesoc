package operacionComercial;


import ProveedorDocComer.DocumentoComercial;
import ProveedorDocComer.Proveedor;
import organizacion.Organizacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OperacionEgreso extends OperacionComercial {
    private MedioDePago medioDePago;
    private String numeroIdentificadorMedioPago;
    private Proveedor proveedor;
    private Organizacion organizacion;
    private List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
    private OperacionIngreso ingresoAsociado;

    public MedioDePago getMedioDePago() {  return medioDePago; }
    public String getNumeroIdentificadorMedioPago() { return numeroIdentificadorMedioPago; }
    public Proveedor getProveedor() { return proveedor; }
    public Organizacion getOrganizacion() { return organizacion; }
    public List<Presupuesto> getPresupuestos() { return presupuestos; }

    public void asociarPresupuesto(Presupuesto presupuesto) {
        this.presupuestos.add(presupuesto);
    }
    public void setMedioDePago(MedioDePago medioDePago) { this.medioDePago = medioDePago; }
    public void setNumeroIdentificadorMedioPago(String numeroIdentificadorMedioPago) { this.numeroIdentificadorMedioPago = numeroIdentificadorMedioPago; }
    public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }
    public void setOrganizacion(Organizacion organizacion) { this.organizacion = organizacion; }
    public void setPresupuestos(List<Presupuesto> presupuestos) { this.presupuestos = presupuestos; }
}
