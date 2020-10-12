package domain.entities.validacionEgresos;

import domain.entities.operacionComercial.EntidadPersistente;
import domain.entities.operacionComercial.OperacionEgreso;

import javax.persistence.*;

@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipoCriterio")
public abstract class CriterioValidacionEgresosPresupuesto extends EntidadPersistente {

	public abstract void validar (OperacionEgreso operacion) throws Exception;
}
