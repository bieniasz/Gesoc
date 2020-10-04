package organizacion;

import direccion.Direccion;
import organizacion.categoria.Categoria;
import usuario.Usuario;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class EntidadJuridica extends Organizacion {

    @Column
    private String razonSocial;
    @Column
    private String nombreFicticio;
    @Column
    private Integer cuit;

    @OneToOne
    private Direccion direccionPostal;

    @OneToMany
    private List<EntidadBase> entidadesBase;

    @Column
    private String codigoIGJ;

    @OneToOne
    private Categoria categoria;

    @OneToMany
    private List<Usuario> listaUsuarios;

}
