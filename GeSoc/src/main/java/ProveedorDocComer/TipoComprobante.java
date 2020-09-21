package ProveedorDocComer;

import operacionComercial.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class TipoComprobante extends EntidadPersistente {
    @Column
    private String descripcion;

public void altaTipoComprobante(String descripcionComprobante){
    TipoComprobante TipoNuevoComprobante = new  TipoComprobante();
    TipoNuevoComprobante.descripcion=descripcionComprobante;
    }

}


