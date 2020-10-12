package domain.entities.operacionComercial.builder;

import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.operacionComercial.MedioDePago;
import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.OperacionIngreso;
import domain.entities.operacionComercial.builder.Exception.*;
import domain.entities.organizacion.Organizacion;

public class OperacionEgresoBuilder extends OperacionComercialBuilder{
    private MedioDePago medioDePago;
    private String numeroIdentificadorMedioPago;
    private Proveedor proveedor;
    private Organizacion organizacion;
    private Integer cantEsperadaPresupuestos;
    private OperacionIngreso ingresoAsociado;

    public OperacionEgresoBuilder() {
        this.setOperacion(new OperacionEgreso());
    }

    public OperacionEgresoBuilder setMedioDePago(MedioDePago medioDePago) {
        this.medioDePago = medioDePago;
        return this;
    }
    public OperacionEgresoBuilder setNumeroIdentificadorMedioPago(String numeroIdentificadorMedioPago) {
        this.numeroIdentificadorMedioPago = numeroIdentificadorMedioPago;
        return this;
    }
    public OperacionEgresoBuilder setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
        return this;
    }
    public OperacionEgresoBuilder setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
        return this;
    }
    public OperacionEgresoBuilder setCantEsperadaPresupuestos(Integer cantEsperadaPresupuestos){
        this.cantEsperadaPresupuestos = cantEsperadaPresupuestos;
        return this;
    }
    public OperacionEgresoBuilder setIngresoAsociado(OperacionIngreso ingresoAsociado) {
        this.ingresoAsociado = ingresoAsociado;
        return this;
    }


    public OperacionEgreso build() throws Exception {
        OperacionEgreso operacion = (OperacionEgreso) super.build();

        if ( this.medioDePago == null )
            throw new FaltaMedioDePagoException();
        if ( this.numeroIdentificadorMedioPago == null )
            throw new FaltaNumeroIdentificadorMedioPagoException();
        if ( this.proveedor == null)
            throw new FaltaProveedorException();
        if ( this.organizacion == null )
            throw new FaltaOrganizacionException();
        if ( this.cantEsperadaPresupuestos == null )
            throw new FaltaCantidadPresupuestosException();

        operacion.setMedioDePago(this.medioDePago);
        operacion.setNumeroIdentificadorMedioPago(this.numeroIdentificadorMedioPago);
        operacion.setProveedor(this.proveedor);
        operacion.setOrganizacion(this.organizacion);
        operacion.setCantidadEsperadaPresupuestos(this.cantEsperadaPresupuestos);

        return operacion;
    }
}
