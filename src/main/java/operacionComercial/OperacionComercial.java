package operacionComercial;


import ProveedorDocComer.DocumentoComercial;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public abstract class OperacionComercial {
    private LocalDate fecha;
    private Double valorTotal;
    private DocumentoComercial documentoComercial;
    private List<DetalleEgreso> detalle = new ArrayList<>();
    private List<CategoriaDeOperaciones> categoriasAsociadas = new ArrayList<>();

    public LocalDate getFecha() { return fecha; }
    public Double getValorTotal() { return valorTotal; }
    public DocumentoComercial getDocumentoComercial() { return documentoComercial; }
    public List<DetalleEgreso> getDetalle() { return detalle; }
    public List<CategoriaDeOperaciones> getCategoriasAsociadas() { return categoriasAsociadas; }

    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setDetalle(List<DetalleEgreso> detalle) { this.detalle = detalle; }
    public void setCategoriasAsociadas(List<CategoriaDeOperaciones> categorias) { this.categoriasAsociadas = categorias; }


    public void calcularValorTotal()  {
        this.valorTotal = this.detalle.stream().mapToDouble( (DetalleEgreso detalle) -> detalle.valorTotal ).sum();
    }

    public void registrarDocumentoComercial(DocumentoComercial documentoComercial) {
        this.documentoComercial = documentoComercial;
    }

    public void registrarDetalle(DetalleEgreso detalle) {
        this.detalle.add(detalle);
        this.calcularValorTotal();
    }

    public void quitarDetalle(DetalleEgreso detalle) {
        this.detalle.remove(detalle);
        this.calcularValorTotal();
    }

    public void asociarACategoria(CategoriaDeOperaciones categoriaOperacion){
        this.categoriasAsociadas.add(categoriaOperacion);
    }



         
	

}
