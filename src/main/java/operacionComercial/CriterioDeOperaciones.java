package operacionComercial;
import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class CriterioDeOperaciones extends EntidadPersistente{

@OneToMany
    private List<CategoriaDeOperaciones> Categorias;

@OneToMany
    private List<CriterioDeOperaciones> CriteriosHijos;
}
