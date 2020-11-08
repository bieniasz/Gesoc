package domain.entities.organizacion;

import domain.entities.direccion.Direccion;
import domain.entities.organizacion.categoria.Categoria;
import domain.entities.usuario.Usuario;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@DiscriminatorValue("EntidadJuridica")
public class EntidadJuridica extends Organizacion {

    public EntidadJuridica() {
    }

    @Column
    private String razonSocial;
    @Column
    private String nombreFicticio;
    @Column
    private String cuit;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private Direccion direccionPostal;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<EntidadBase> entidadesBase;

    @Column
    private String codigoIGJ;

    @OneToOne
    private Categoria categoria;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="organizacion",referencedColumnName = "id")
    private List<Usuario> listaUsuarios;

    // ACCESORES
    public String getRazonSocial() { return razonSocial; }
    @Override
    public String getNombreFicticio() { return nombreFicticio; }
    public String getCuit() { return cuit; }
    public Direccion getDireccionPostal() { return direccionPostal; }
    public List<EntidadBase> getEntidadesBase() { return entidadesBase; }
    public String getCodigoIGJ() { return codigoIGJ; }
    public Categoria getCategoria() { return categoria; }
    public List<Usuario> getListaUsuarios() { return listaUsuarios; }

    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
    public void setNombreFicticio(String nombreFicticio) { this.nombreFicticio = nombreFicticio; }
    public void setCuit(String cuit) { this.cuit = cuit; }
    public void setDireccionPostal(Direccion direccionPostal) { this.direccionPostal = direccionPostal; }
    public void setEntidadesBase(List<EntidadBase> entidadesBase) { this.entidadesBase = entidadesBase; }
    public void setCodigoIGJ(String codigoIGJ) { this.codigoIGJ = codigoIGJ; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public void setListaUsuarios(List<Usuario> listaUsuarios) { this.listaUsuarios = listaUsuarios; }

}
