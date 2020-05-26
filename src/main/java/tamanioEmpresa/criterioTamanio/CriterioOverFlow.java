package tamanioEmpresa.criterioTamanio;

import organizacion.categoria.Empresa;
import tamanioEmpresa.Tamanios;

public class CriterioOverFlow extends CriterioTamanioEmpresa {
	public CriterioOverFlow() {
		this.tamanio = Tamanios.OVERFLOW;
	}

	@Override
	public Boolean esCategoria(Empresa empresa) {
		return  empresa.getPromedioVentasAnuales() > empresa.getActividad().getTopeMedianaT2();
	}
}
