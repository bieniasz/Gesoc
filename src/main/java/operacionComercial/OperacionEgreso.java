package operacionComercial;


import organizacion.Organizacion;

import java.time.LocalDate;
import java.util.List;

public class OperacionEgreso {
    private LocalDate fecha;
    private float valorTotal;
    private MedioDePago medioDePago;
    private String numeroIdentificadorMedioPago;
    //private DocumentoComercial documentoComercial;
    //private Proveedor proveedor;
    private Organizacion organizacion;
    private List<DetalleEgreso> detalle;

    public void altaOperacionEgreso(){}
    public void calcularValorTotal(){}
    public void registrarDetalle(){}
    public void registrarDocumentoComercial(){}
    public void validarExistenciaProveedor(){}
    public void validarExistenciaMedioDePago(){}

}
