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


    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombreFicticio() {
        return nombreFicticio;
    }

    public void setNombreFicticio(String nombreFicticio) {
        this.nombreFicticio = nombreFicticio;
    }

    public Integer getCuit() {
        return cuit;
    }

    public void setCuit(Integer cuit) {
        this.cuit = cuit;
    }

    public Direccion getDireccionPostal() {
        return direccionPostal;
    }

    public void setDireccionPostal(Direccion direccionPostal) {
        this.direccionPostal = direccionPostal;
    }

    public List<EntidadBase> getEntidadesBase() {
        return entidadesBase;
    }

    public void setEntidadesBase(List<EntidadBase> entidadesBase) {
        this.entidadesBase = entidadesBase;
    }

    public String getCodigoIGJ() {
        return codigoIGJ;
    }

    public void setCodigoIGJ(String codigoIGJ) {
        this.codigoIGJ = codigoIGJ;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
}
