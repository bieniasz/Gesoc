package validacionEgresos;

import java.util.ArrayList;

import operacionComercial.EntidadPersistente;
import operacionComercial.OperacionEgreso;
import operacionComercial.Presupuesto;

import javax.persistence.*;

@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="<<tipoCriterio>>")
public abstract class CriterioValidacionEgresosPresupuesto extends EntidadPersistente {

	private String tipoCriterio;
	public abstract void validar (OperacionEgreso operacion) throws Exception;
}
