package operacionComercial;


import ProveedorDocComer.DocumentoComercial;
import ProveedorDocComer.Proveedor;
import organizacion.Organizacion;

import java.time.LocalDate;
import java.util.List;

public class OperacionEgreso extends OperacionComercial {
    private MedioDePago medioDePago;
    private String numeroIdentificadorMedioPago;
    private Proveedor proveedor;
    private Organizacion organizacion;
    private List<Presupuesto> presupuestos;
    //private OperacionIngreso ingresoAsociado;

    // TODO descomentar todo cuando exista la clase OperacionIngreso

    public OperacionEgreso(LocalDate fecha, float valorTotal, DocumentoComercial docComercial, List<DetalleEgreso> detalle, MedioDePago medio, String numIdMedioPago, Proveedor proveedor, Organizacion organizacion, List<Presupuesto> presupuestos) {
        super(fecha, valorTotal, docComercial, detalle);
        this.medioDePago = medio;
        this.numeroIdentificadorMedioPago = numIdMedioPago;
        this.proveedor = proveedor;
        this.organizacion = organizacion;
        this.presupuestos = presupuestos;
    }

    @Override
    public Double calcularValorTotal() {
    	return this.getDetalle().stream().mapToDouble(d->d.getValorTotal()).sum();
    }
    @Override
    public void registrarDetalle() {   }
    @Override
    public void registrarDocumentoComercial() {   }
    public void altaOperacionEgreso() { }
    public void validarExistenciaProveedor() { }
    public void validarExistenciaMedioDePago() { }
    public void informarItemsYCantidadesAPresupuesto() { }
    public void asociarPresupuesto(Presupuesto presupuesto, boolean esElElegido) { }


    /* GETTERS & SETTERS */
    public MedioDePago getMedioDePago() {  return medioDePago; }
    public String getNumeroIdentificadorMedioPago() { return numeroIdentificadorMedioPago; }
    public Proveedor getProveedor() { return proveedor; }
    public Organizacion getOrganizacion() { return organizacion; }
    public List<Presupuesto> getPresupuestos() { return presupuestos; }

    public void setMedioDePago(MedioDePago medioDePago) { this.medioDePago = medioDePago; }
    public void setNumeroIdentificadorMedioPago(String numeroIdentificadorMedioPago) {
        this.numeroIdentificadorMedioPago = numeroIdentificadorMedioPago;
    }
    public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }
    public void setOrganizacion(Organizacion organizacion) { this.organizacion = organizacion; }
    public void setPresupuestos(List<Presupuesto> presupuestos) { this.presupuestos = presupuestos; }
}
