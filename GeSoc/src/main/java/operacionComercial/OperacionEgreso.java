package operacionComercial;


import ProveedorDocComer.DocumentoComercial;
import ProveedorDocComer.Proveedor;
import organizacion.Organizacion;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@DiscriminatorValue("Egreso")
public class OperacionEgreso extends OperacionComercial {

    @ManyToOne
    private MedioDePago medioDePago;

    @Column
    private String numeroIdentificadorMedioPago;

    @ManyToOne
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "Organizacion",referencedColumnName = "id")
    private Organizacion organizacion;

    @OneToMany
    private List<Presupuesto> presupuestos;

    @ManyToOne
    private OperacionIngreso ingresoAsociado;

    public OperacionEgreso(LocalDate fecha, float valorTotal, DocumentoComercial docComercial, List<DetalleEgreso> detalle, MedioDePago medio, String numIdMedioPago, Proveedor proveedor, Organizacion organizacion) {
        super(fecha, valorTotal, docComercial, detalle);
        this.medioDePago = medio;
        this.numeroIdentificadorMedioPago = numIdMedioPago;
        this.proveedor = proveedor;
        this.organizacion = organizacion;
        this.presupuestos = new ArrayList<Presupuesto>();
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

    public void asociarPresupuesto(Presupuesto presupuesto) {
        this.presupuestos.add(presupuesto);
    }


    public MedioDePago getMedioDePago() {  return medioDePago; }
    public String getNumeroIdentificadorMedioPago() { return numeroIdentificadorMedioPago; }
    public Proveedor getProveedor() { return proveedor; }
    public Organizacion getOrganizacion() { return organizacion; }
    public Integer getCantidadEsperadaPresupuestos() { return cantidadEsperadaPresupuestos; }
    public List<Presupuesto> getPresupuestos() { return presupuestos; }

    public void asociarPresupuesto(Presupuesto presupuesto) {
        this.presupuestos.add(presupuesto);
    }
    public void setMedioDePago(MedioDePago medioDePago) { this.medioDePago = medioDePago; }
    public void setNumeroIdentificadorMedioPago(String numeroIdentificadorMedioPago) { this.numeroIdentificadorMedioPago = numeroIdentificadorMedioPago; }
    public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }
    public void setOrganizacion(Organizacion organizacion) { this.organizacion = organizacion; }
    public void setCantidadEsperadaPresupuestos(Integer cantidadEsperadaPresupuestos) {
        this.cantidadEsperadaPresupuestos = cantidadEsperadaPresupuestos;
    }
    public void setPresupuestos(List<Presupuesto> presupuestos) { this.presupuestos = presupuestos; }
    public void setIngresoAsociado(OperacionIngreso operacionIngreso){
        this.ingresoAsociado = operacionIngreso;
    }
}
