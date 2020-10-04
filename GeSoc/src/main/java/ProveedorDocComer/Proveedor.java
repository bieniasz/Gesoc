package ProveedorDocComer;

import direccion.Direccion;
import operacionComercial.EntidadPersistente;

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


public void altaProveedor(String nomApRazonSocial,String tipoIdentificador,Long numeroIdenti,Direccion direccionPost,String estado){

    Proveedor ProveedorNuevo = new Proveedor();
    ProveedorNuevo.nombreApellido_RazonSocial=nomApRazonSocial;
    ProveedorNuevo.tipoIdentificacion=tipoIdentificador;
    ProveedorNuevo.numeroIdentificacion=numeroIdenti;
    ProveedorNuevo.direccionPostal=direccionPost;
    ProveedorNuevo.estado=estado;

}

}


