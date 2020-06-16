package tamanioEmpresa.criterioTamanio;

import organizacion.categoria.Empresa;
import tamanioEmpresa.Tamanios;

public class CriterioMedianaTramo2 extends CriterioTamanioEmpresa {
	public CriterioMedianaTramo2() {
		this.tamanio = Tamanios.MEDIANA_T2;
	}

	@Override
	public Boolean esCategoria(Empresa empresa) {
			         
	return  empresa.getPromedioVentasAnuales() <= empresa.getActividad().getTopeMedianaT2() &&
			empresa.getPromedioVentasAnuales() > empresa.getActividad().getTopeMedianaT1()
			;
	}
}
