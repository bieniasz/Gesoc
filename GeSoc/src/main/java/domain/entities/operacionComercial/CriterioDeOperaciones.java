package domain.entities.operacionComercial;
import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class CriterioDeOperaciones extends EntidadPersistente{

    public CriterioDeOperaciones() {
    }

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="CriterioDeOperaciones",referencedColumnName = "id")
    private List<CategoriaDeOperaciones> Categorias;

    @Column
    private boolean activo;

    @Column
    private String descripcion;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="CriterioDeOperaciones",referencedColumnName = "id")
    private List<CriterioDeOperaciones> CriteriosHijos;
}
