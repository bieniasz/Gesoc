package operacionComercial;


import ProveedorDocComer.DocumentoComercial;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.javatuples.Pair;

import javax.persistence.*;


@Entity
@Table(name="OperacionComercial")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "<<TipoOperacion>>")
public abstract class OperacionComercial extends EntidadPersistente {

    @Column
    private String TipoOperacion;

    @Transient
    private LocalDate fecha;

    @Column(name="valorTotal")
    public float valorTotal;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private DocumentoComercial documentoComercial;

    @OneToMany
    private List<DetalleEgreso> detalle;

    @OneToMany
    private List<CategoriaDeOperaciones> categoriasAsociadas;

    // TODO descomentar todo cuando exista la clase CategoriaOperacion

    public OperacionComercial(LocalDate fecha, float valorTotal, DocumentoComercial docComercial, List<DetalleEgreso> detalle){
        this.fecha = fecha;
        this.valorTotal = valorTotal;
        this.documentoComercial = docComercial;
        this.detalle = detalle;
        //this.categoriasAsociadas = categorias;
    }

    public abstract Double calcularValorTotal();
    public abstract void registrarDetalle();
    public abstract void registrarDocumentoComercial();
    //public abstract void asociarACategoria(CategoriaOperacion categoriaOperacion);


    /* GETTERS & SETTERS */
    public LocalDate getFecha() { return fecha; }
    public float getValorTotal() { return valorTotal; }
    public DocumentoComercial getDocumentoComercial() { return documentoComercial; }
    public List<DetalleEgreso> getDetalle() { return detalle; }

    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setValorTotal(float valorTotal) { this.valorTotal = valorTotal; }
    public void setDocumentoComercial(DocumentoComercial documentoComercial) { this.documentoComercial = documentoComercial; }
    public void setDetalle(List<DetalleEgreso> detalle) { this.detalle = detalle; }
    
         
	

}
