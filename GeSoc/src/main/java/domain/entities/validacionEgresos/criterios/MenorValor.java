package domain.entities.validacionEgresos.criterios;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import domain.entities.operacionComercial.OperacionComercial;
import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.Presupuesto;
import domain.entities.validacionEgresos.CriterioValidacionEgresosPresupuesto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MenorValor")
public class MenorValor extends CriterioValidacionEgresosPresupuesto {

	@Override
	public void validar(OperacionEgreso operacion) throws Exception {
		//ordena de menor a mayor, por eso traigo el primero
		/*Presupuesto menorValor = operacion
				.getPresupuestos()
				.stream()
				.min(Comparator.comparing(OperacionComercial::getValorTotal))
				.get(); */
		List<Presupuesto> lstPresupuestosMinimos = getPresupuestosMenorValor(operacion);
		Presupuesto elegido = operacion
				.getPresupuestos()
				.stream()
				.filter(Presupuesto::isElegido)
				.findFirst()
				.get();

		if (!lstPresupuestosMinimos.contains(elegido)) {
			throw new Exception("El presupuesto elegido no coincide con el/los de menor valor");
		};
	}

	private List<Presupuesto> getPresupuestosMenorValor(OperacionEgreso op) {
		List<Presupuesto> lstPresupuestosMinimos = new ArrayList<Presupuesto>();

		Presupuesto presupuestoMenorValor = op
				.getPresupuestos()
				.stream()
				.min(Comparator.comparing(OperacionComercial::getValorTotal))
				.get();

		//obtengo todos los presupuestos con el mismo valorTotal minimo
		lstPresupuestosMinimos = op.getPresupuestos()
				.stream()
				.filter(p -> p.getValorTotal() == presupuestoMenorValor.getValorTotal())
				.collect(Collectors.toList());

		return lstPresupuestosMinimos;
	}

}
