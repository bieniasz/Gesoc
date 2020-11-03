package domain.entities.operacionComercial;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="CategoriaDeOperaciones")
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

    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="OperacionComercial",referencedColumnName = "id")
    private List<OperacionComercial> operacionComercial;


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

    public List<OperacionComercial> getOperacionComercial() {
        return operacionComercial;
    }

    public void setOperacionComercial(List<OperacionComercial> operacionComercial) {
        this.operacionComercial = operacionComercial;
    }

    public void agregarOperacion(OperacionComercial operacionComercial) {
        if (this.operacionComercial == null) {
            this.operacionComercial = new ArrayList<>();
        }

        this.operacionComercial.add(operacionComercial);
    }
}
