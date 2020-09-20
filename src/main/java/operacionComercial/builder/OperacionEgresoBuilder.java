package operacionComercial.builder;

import ProveedorDocComer.Proveedor;
import operacionComercial.MedioDePago;
import operacionComercial.OperacionEgreso;
import operacionComercial.OperacionIngreso;
import operacionComercial.Presupuesto;
import operacionComercial.builder.Exception.FaltaMedioDePagoException;
import operacionComercial.builder.Exception.FaltaNumeroIdentificadorMedioPagoException;
import operacionComercial.builder.Exception.FaltaOrganizacionException;
import operacionComercial.builder.Exception.FaltaProveedorException;
import organizacion.Organizacion;

import java.util.ArrayList;
import java.util.List;

public class OperacionEgresoBuilder extends OperacionComercialBuilder{
    private MedioDePago medioDePago;
    private String numeroIdentificadorMedioPago;
    private Proveedor proveedor;
    private Organizacion organizacion;
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

        operacion.setMedioDePago(this.medioDePago);
        operacion.setNumeroIdentificadorMedioPago(this.numeroIdentificadorMedioPago);
        operacion.setProveedor(this.proveedor);
        operacion.setOrganizacion(this.organizacion);

        return operacion;
    }
}
