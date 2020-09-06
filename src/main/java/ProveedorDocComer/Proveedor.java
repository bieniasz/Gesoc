package ProveedorDocComer;

import direccion.Direccion;

public class Proveedor {

    private String nombreApellido_RazonSocial;
    private String tipoIdentificacion;
    private Long numeroIdentificacion;
    private Direccion direccionPostal ;
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


