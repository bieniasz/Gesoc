package organizacion;

import organizacion.categoria.Categoria;
import usuario.Usuario;

import java.util.List;

public class EntidadJuridica extends Organizacion {
    private String razonSocial;
    private String nombreFicticio;
    private Integer cuit;
    private Integer direccionPostal;
    private List<EntidadBase> entidadesBase;
    private String codigoIGJ;
    private Categoria categoria;
    private List<Usuario> listaUsuarios;

}
