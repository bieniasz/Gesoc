package operacionComercial;

import javax.persistence.*;

@Entity
@Table
public class CategoriaDeOperaciones extends EntidadPersistente {

@Column
    private String descripcion;

@ManyToOne
    private CriterioDeOperaciones criterioDeCategoria;
}
