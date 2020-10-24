package domain.entities.organizacion;

import javax.persistence.*;

@Entity
@Table
public class EntidadBase extends Organizacion {

    @Column
    private String nombreFicticio;

    @Column
    private String descripcion;


    @ManyToOne
    @JoinColumn(name="entidadJuridica",referencedColumnName = "id")
    private EntidadJuridica entidadJuridica;

    //ACCESORES
    public String getNombreFicticio() {
        return nombreFicticio;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public EntidadJuridica getEntidadJuridica() {
        return entidadJuridica;
    }

    public void setNombreFicticio(String nombreFicticio) {
        this.nombreFicticio = nombreFicticio;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setEntidadJuridica(EntidadJuridica entidadJuridica) {
        this.entidadJuridica = entidadJuridica;
    }

}
