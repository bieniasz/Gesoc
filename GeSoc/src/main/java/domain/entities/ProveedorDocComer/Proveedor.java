package domain.entities.ProveedorDocComer;

import domain.entities.direccion.Direccion;
import domain.entities.operacionComercial.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table
public class Proveedor extends EntidadPersistente {

    @Column
    private String nombreApellido_RazonSocial;

    @Column
    private String tipoIdentificacion;

    @Column
    private Long numeroIdentificacion;

    @OneToOne
    private Direccion direccionPostal ;

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

    public void altaProveedor(String nomApRazonSocial, String tipoIdentificador, Long numeroIdenti, Direccion direccionPost, String estado){

    Proveedor ProveedorNuevo = new Proveedor();
    ProveedorNuevo.nombreApellido_RazonSocial=nomApRazonSocial;
    ProveedorNuevo.tipoIdentificacion=tipoIdentificador;
    ProveedorNuevo.numeroIdentificacion=numeroIdenti;
    ProveedorNuevo.direccionPostal=direccionPost;
    ProveedorNuevo.estado=estado;

}

}


