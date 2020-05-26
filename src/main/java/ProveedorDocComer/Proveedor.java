package ProveedorDocComer;

public class Proveedor {

    private String nombreApellido_RazonSocial;
    private String tipoIdentificacion;
    private Long numeroIdentificacion;
    private String direccionPostal ;
    private String estado;


public void altaProveedor(String nomApRazonSocial,String tipoIdentificador,Long numeroIdenti,String direccionPost,String estado){

    Proveedor ProveedorNuevo = new Proveedor();
    ProveedorNuevo.nombreApellido_RazonSocial=nomApRazonSocial;
    ProveedorNuevo.tipoIdentificacion=tipoIdentificador;
    ProveedorNuevo.numeroIdentificacion=numeroIdenti;
    ProveedorNuevo.direccionPostal=direccionPost;
    ProveedorNuevo.estado=estado;

}

}


