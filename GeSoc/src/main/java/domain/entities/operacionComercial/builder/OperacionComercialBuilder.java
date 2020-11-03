package domain.entities.operacionComercial.builder;

import domain.entities.ProveedorDocComer.DocumentoComercial;
import domain.entities.operacionComercial.CategoriaDeOperaciones;
import domain.entities.operacionComercial.DetalleEgreso;
import domain.entities.operacionComercial.OperacionComercial;
import domain.entities.operacionComercial.builder.Exception.FaltaDetalleException;
import domain.entities.operacionComercial.builder.Exception.FaltaFechaException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class OperacionComercialBuilder {

    private DocumentoComercial documentoComercial;
    private List<DetalleEgreso> detalle = new ArrayList<DetalleEgreso>();
    private List<CategoriaDeOperaciones> categoriasAsociadas = new ArrayList<CategoriaDeOperaciones>();
    private LocalDate fecha;
    private OperacionComercial operacion;

    public OperacionComercialBuilder setOperacion(OperacionComercial operacion) {
        this.operacion = operacion;
        return this;
    }
    public OperacionComercialBuilder setDocumentoComercial(DocumentoComercial documentoComercial) {
        this.documentoComercial = documentoComercial;
        return this;
    }
    public OperacionComercialBuilder setDetalle(List<DetalleEgreso> detalle) {
        this.detalle = detalle;
        return this;
    }
    public OperacionComercialBuilder setCategoriasAsociadas(List<CategoriaDeOperaciones> categoriasAsociadas) {
        this.categoriasAsociadas = categoriasAsociadas;
        return this;
    }
    public OperacionComercialBuilder setFecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public OperacionComercial build() throws Exception {
        if( this.categoriasAsociadas.size() != 0)
            this.operacion.setCategoriasAsociadas(this.categoriasAsociadas);
            this.operacion.getCategoriasAsociadas().forEach( categoria -> categoria.agregarOperacion(this.operacion));

        if( this.documentoComercial != null)
            this.operacion.registrarDocumentoComercial(this.documentoComercial);

        if( this.detalle.size() == 0 )
            throw new FaltaDetalleException();

        if( this.fecha == null )
            throw new FaltaFechaException();

        this.operacion.setDetalle(this.detalle);
        this.operacion.actualizarValorTotal();
        this.operacion.setFecha(this.fecha);


        return this.operacion;
    }


}
