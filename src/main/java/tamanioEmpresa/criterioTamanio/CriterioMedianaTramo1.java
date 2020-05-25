package tamanioEmpresa.criterioTamanio;

import organizacion.categoria.Empresa;

public class CriterioMedianaTramo1 extends CriterioTamanioEmpresa {
	public CriterioMedianaTramo1() {
		this.tamanio = "Mediana_tramo_1";
	}

	@Override
	public Boolean esCategoria(Empresa empresa) {
		return  empresa.getPromedioVentasAnuales() <= empresa.getActividad().getTopeMedianaT1() &&
				empresa.getPromedioVentasAnuales() > empresa.getActividad().getTopePequenia()
				;
	}
}
