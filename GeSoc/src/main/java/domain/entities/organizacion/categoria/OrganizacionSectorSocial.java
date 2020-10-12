package domain.entities.organizacion.categoria;


import javax.persistence.*;

@Entity
@DiscriminatorValue("OrganizacionSectorSocial")
public class OrganizacionSectorSocial extends Categoria {

    @Column
    private String areaDeTrabajo;
    @Column
    private Integer cantidadDePersonal;

    public OrganizacionSectorSocial(String areaDeTrabajo, Integer cantPersonal){
        this.areaDeTrabajo = areaDeTrabajo;
        this.cantidadDePersonal = cantPersonal;
    }

    public String getAreaDeTrabajo() {
        return areaDeTrabajo;
    }

    public void setAreaDeTrabajo(String areaDeTrabajo) {
        this.areaDeTrabajo = areaDeTrabajo;
    }

    public Integer getCantidadDePersonal() {
        return cantidadDePersonal;
    }

    public void setCantidadDePersonal(Integer cantidadDePersonal) {
        this.cantidadDePersonal = cantidadDePersonal;
    }
}
