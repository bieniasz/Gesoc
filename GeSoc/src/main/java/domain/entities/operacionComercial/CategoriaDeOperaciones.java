package domain.entities.operacionComercial;

import javax.persistence.*;

@Entity
@Table
public class CategoriaDeOperaciones extends EntidadPersistente {

@Column
    private String descripcion;

@Column
private boolean activo;

@ManyToOne
    private CriterioDeOperaciones criterioDeCategoria;
}
