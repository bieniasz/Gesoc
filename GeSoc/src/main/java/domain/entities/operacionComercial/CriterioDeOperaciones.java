package domain.entities.operacionComercial;
import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class CriterioDeOperaciones extends EntidadPersistente{

@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<CategoriaDeOperaciones> Categorias;

@Column
    private boolean activo;

@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<CriterioDeOperaciones> CriteriosHijos;
}
