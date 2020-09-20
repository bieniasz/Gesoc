package operacionComercial;
import javax.persistence.*;
import java.util.List;

@Entity
@Table

public class CriterioDeOperaciones extends EntidadPersistente{

@Transient
    private List<CategoriaDeOperaciones> Categorias;

@Transient
    private List<CriterioDeOperaciones> CriteriosHijos;
}
