package domain.entities.ProveedorDocComer;

import domain.entities.direccion.Direccion;
import domain.entities.operacionComercial.EntidadPersistente;
import domain.entities.operacionComercial.Item;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Proveedor extends EntidadPersistente {

    public Proveedor() {
    }

    @Column
    private String nombreApellido_RazonSocial;

    @Column
    private String tipoIdentificacion;

    @Column
    private Long numeroIdentificacion;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Direccion direccionPostal ;

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Item> items;

    @Column
    private String estado;
    @Column
    private boolean activo;

    public String getNombreApellido_RazonSocial() {
        return nombreApellido_RazonSocial;
    }

    public void setNombreApellido_RazonSocial(String nombreApellido_RazonSocial) {
        this.nombreApellido_RazonSocial = nombreApellido_RazonSocial;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public Long getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(Long numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public Direccion getDireccionPostal() {
        return direccionPostal;
    }

    public void setDireccionPostal(Direccion direccionPostal) {
        this.direccionPostal = direccionPostal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void altaProveedor(String nomApRazonSocial, String tipoIdentificador, Long numeroIdenti, Direccion direccionPost, String estado){

    Proveedor ProveedorNuevo = new Proveedor();
    ProveedorNuevo.nombreApellido_RazonSocial=nomApRazonSocial;
    ProveedorNuevo.tipoIdentificacion=tipoIdentificador;
    ProveedorNuevo.numeroIdentificacion=numeroIdenti;
    ProveedorNuevo.direccionPostal=direccionPost;
    ProveedorNuevo.estado=estado;

}

}


