package operacionComercial.builder;

import ProveedorDocComer.DocumentoComercial;
import operacionComercial.CategoriaDeOperaciones;
import operacionComercial.DetalleEgreso;
import operacionComercial.OperacionComercial;
import operacionComercial.builder.Exception.FaltaDetalleException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class OperacionComercialBuilder {

    private DocumentoComercial documentoComercial;
    private List<DetalleEgreso> detalle = new ArrayList<DetalleEgreso>();
    private List<CategoriaDeOperaciones> categoriasAsociadas = new ArrayList<CategoriaDeOperaciones>();
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

    public OperacionComercial build() throws Exception {
        if( this.categoriasAsociadas.size() != 0)
            this.operacion.setCategoriasAsociadas(this.categoriasAsociadas);

        if( this.documentoComercial != null)
            this.operacion.registrarDocumentoComercial(this.documentoComercial);

        if( this.detalle.size() == 0 )
            throw new FaltaDetalleException();

        this.operacion.setDetalle(this.detalle);
        this.operacion.actualizarValorTotal();
        this.operacion.setFecha(LocalDate.now());


        return this.operacion;
    }


}
