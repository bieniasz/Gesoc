package tamanioEmpresa.criterioTamanio;

import organizacion.categoria.Empresa;
import tamanioEmpresa.Tamanios;

public class CriterioMicro extends CriterioTamanioEmpresa {
	public CriterioMicro() {
		this.tamanio = Tamanios.MICRO;
	}

	@Override	
	public Boolean esCategoria(Empresa empresa) {
		return  empresa.getPromedioVentasAnuales() <= empresa.getActividad().getTopeMicro();
	}
	
}
