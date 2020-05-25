package tamanioEmpresa.criterioTamanio;

import organizacion.categoria.Empresa;

public class CriterioPequenia extends CriterioTamanioEmpresa {

	public CriterioPequenia() {
		this.tamanio = "Pequeña";
	}

	@Override	
	public Boolean esCategoria(Empresa empresa) {
		return  empresa.getPromedioVentasAnuales() <= empresa.getActividad().getTopePequenia()
				&& empresa.getPromedioVentasAnuales() > empresa.getActividad().getTopeMicro();
	}
}
