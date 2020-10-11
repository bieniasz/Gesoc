package operacionComercial;
import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class CriterioDeOperaciones extends EntidadPersistente{

@OneToMany
    private List<CategoriaDeOperaciones> Categorias;

@Column
    private boolean activo;

@OneToMany
    private List<CriterioDeOperaciones> CriteriosHijos;
}
