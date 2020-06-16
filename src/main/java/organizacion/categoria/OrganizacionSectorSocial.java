package organizacion.categoria;

public class OrganizacionSectorSocial implements Categoria {
    private String areaDeTrabajo;
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
