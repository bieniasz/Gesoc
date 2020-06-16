package tamanioEmpresa.criterioTamanio;

import organizacion.categoria.Empresa;
import tamanioEmpresa.Tamanios;

public class CriterioPequenia extends CriterioTamanioEmpresa {

	public CriterioPequenia() {
		this.tamanio = Tamanios.PEQUENIA;
	}

	@Override	
	public Boolean esCategoria(Empresa empresa) {
		return  empresa.getPromedioVentasAnuales() <= empresa.getActividad().getTopePequenia()
				&& empresa.getPromedioVentasAnuales() > empresa.getActividad().getTopeMicro();
	}
}
