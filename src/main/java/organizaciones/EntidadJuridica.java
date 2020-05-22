package organizaciones;

import organizaciones.categorias.Categoria;
import java.util.List;

public class EntidadJuridica extends Organizacion {
    private String razonSocial;
    private String nombreFicticio;
    private Integer cuit;
    private Integer direccionPostal;
    private List<EntidadBase> entidadesBase;
    private String codigoIGJ;
    private Categoria categoria;

}
