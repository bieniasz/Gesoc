package domain.entities.operacionComercial;

import javax.persistence.*;

@Entity
@Table
public class CategoriaDeOperaciones extends EntidadPersistente {

    public CategoriaDeOperaciones() {
    }

    @Column
    private String descripcion;

    @Column
    private boolean activo;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="CriterioDeOperaciones",referencedColumnName = "id")
    private CriterioDeOperaciones criterioDeCategoria;


    //ACCESORES
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public CriterioDeOperaciones getCriterioDeCategoria() {
        return criterioDeCategoria;
    }

    public void setCriterioDeCategoria(CriterioDeOperaciones criterioDeCategoria) {
        this.criterioDeCategoria = criterioDeCategoria;
    }
}
