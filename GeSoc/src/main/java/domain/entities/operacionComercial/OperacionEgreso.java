package domain.entities.operacionComercial;


import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.organizacion.Organizacion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@DiscriminatorValue("Egreso")
public class OperacionEgreso extends OperacionComercial {

    public OperacionEgreso() {
    }


    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="medioDePago",referencedColumnName = "id")
    private MedioDePago medioDePago;

    @Column
    private String numeroIdentificadorMedioPago;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "proveedor",referencedColumnName = "id")
    private Proveedor proveedor;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "organizacion",referencedColumnName = "id")
    private Organizacion organizacion;

    @Column
    private Integer cantidadEsperadaPresupuestos;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="presupuestos",referencedColumnName = "id")
    private List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();


    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="ingresoAsociado",referencedColumnName = "id")
    private OperacionIngreso ingresoAsociado;


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


    public OperacionIngreso getIngresoAsociado() {
        return ingresoAsociado;
    }

    public void setIngresoAsociado(OperacionIngreso ingresoAsociado) {
        this.ingresoAsociado = ingresoAsociado;
    }
}
