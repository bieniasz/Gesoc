package domain.entities.operacionComercial;


import domain.entities.ProveedorDocComer.DocumentoComercial;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="OperacionComercial")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TipoOperacion")
public abstract class OperacionComercial extends EntidadPersistente {

    public OperacionComercial() {
    }

    @Column(columnDefinition = "DATE")
    private LocalDate fecha;


    @Column(name="valorTotal")
    public Double valorTotal;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="DocumentoComercial_id",referencedColumnName = "id")
    private DocumentoComercial documentoComercial;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="OperacionComercial",referencedColumnName = "id")
    private List<DetalleEgreso> detalle;

    @ManyToMany(mappedBy = "operacionComercial", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<CategoriaDeOperaciones> categoriasAsociadas;

    public LocalDate getFecha() { return fecha; }
    public Double getValorTotal() { return valorTotal; }
    public DocumentoComercial getDocumentoComercial() { return documentoComercial; }
    public List<DetalleEgreso> getDetalle() { return detalle; }
    public List<CategoriaDeOperaciones> getCategoriasAsociadas() { return categoriasAsociadas; }

    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setValorTotal(Double valorTotal) {this.valorTotal = valorTotal; }
    public void setDocumentoComercial(DocumentoComercial documentoComercial) { this.documentoComercial = documentoComercial; }
    public void setDetalle(List<DetalleEgreso> detalle) {
        this.detalle = detalle;
        actualizarValorTotal();
    }
    public void setCategoriasAsociadas(List<CategoriaDeOperaciones> categorias) { this.categoriasAsociadas = categorias; }


    public void actualizarValorTotal()  {
        this.valorTotal = this.detalle.stream().mapToDouble( (DetalleEgreso detalle) -> detalle.valorTotal ).sum();
    }

    public void registrarDocumentoComercial(DocumentoComercial documentoComercial) {
        this.documentoComercial = documentoComercial;
    }

    public void registrarDetalle(DetalleEgreso detalle) {
        if (this.detalle == null) { this.detalle = new ArrayList<>(); }

        this.detalle.add(detalle);
        this.actualizarValorTotal();
    }

    public void registrarDetalles(List<DetalleEgreso> detallesAAgregar) {
        this.detalle.addAll(detallesAAgregar);
        this.actualizarValorTotal();
    }

    public void quitarDetalle(DetalleEgreso detalle) {
        this.detalle.remove(detalle);
        this.actualizarValorTotal();
    }

    public void quitarDetalles(List<DetalleEgreso> detallesAQuitar) {
        this.detalle.removeAll(detallesAQuitar);
        this.actualizarValorTotal();
    }

    public void asociarACategoria(CategoriaDeOperaciones categoriaOperacion){
        this.categoriasAsociadas.add(categoriaOperacion);
    }

    public void quitarCategoriaPorId(int idDeLaCategoriaARemover){
        this.categoriasAsociadas.removeIf( categoria -> categoria.getId() == idDeLaCategoriaARemover);
    }


}
