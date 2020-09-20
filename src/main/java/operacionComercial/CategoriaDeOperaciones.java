package operacionComercial;

import javax.persistence.*;

@Entity
@Table
public class CategoriaDeOperaciones extends EntidadPersistente {

@Transient
    private String descripcion;

@Transient
    private CriterioDeOperaciones criterioDeCategoria;
}
