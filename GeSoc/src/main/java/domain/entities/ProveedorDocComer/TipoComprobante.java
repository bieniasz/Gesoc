package domain.entities.ProveedorDocComer;

import domain.entities.operacionComercial.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class TipoComprobante extends EntidadPersistente {

    public TipoComprobante() {
    }

    @Column
    private String descripcion;

    @Column
    private boolean activo;

public void altaTipoComprobante(String descripcionComprobante){
    TipoComprobante TipoNuevoComprobante = new  TipoComprobante();
    TipoNuevoComprobante.descripcion=descripcionComprobante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}


