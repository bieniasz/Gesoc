package tamanioEmpresa.criterioTamanio;

import organizacion.categoria.Empresa;

public class CriterioOverFlow extends CriterioTamanioEmpresa {
	public CriterioOverFlow() {
		this.tamanio = "NA";
	}

	@Override	
	public Boolean esCategoria(Empresa empresa) {
		return  empresa.getPromedioVentasAnuales() > empresa.getActividad().getTopeMedianaT2();
	}
}
