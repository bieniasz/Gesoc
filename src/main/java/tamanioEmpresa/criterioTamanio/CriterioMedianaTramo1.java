package tamanioEmpresa.criterioTamanio;

import organizacion.categoria.Empresa;
import tamanioEmpresa.Tamanios;

public class CriterioMedianaTramo1 extends CriterioTamanioEmpresa {
	public CriterioMedianaTramo1() {
		this.tamanio = Tamanios.MEDIANA_T1;
	}

	@Override
	public Boolean esCategoria(Empresa empresa) {
		return  empresa.getPromedioVentasAnuales() <= empresa.getActividad().getTopeMedianaT1() &&
				empresa.getPromedioVentasAnuales() > empresa.getActividad().getTopePequenia()
				;
	}
}
