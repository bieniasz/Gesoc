package domain.entities.organizacion;

import domain.entities.direccion.Direccion;
import domain.entities.organizacion.categoria.Categoria;
import domain.entities.usuario.Usuario;

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
    @JoinColumn(name="listaUsuarios",referencedColumnName = "id")
    private List<Usuario> listaUsuarios;
}
