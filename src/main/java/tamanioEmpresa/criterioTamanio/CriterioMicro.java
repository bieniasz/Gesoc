package tamanioEmpresa.criterioTamanio;

import organizacion.categoria.Empresa;

public class CriterioMicro extends CriterioTamanioEmpresa {
	public CriterioMicro() {
		this.tamanio = "Micro";
	}

	@Override	
	public Boolean esCategoria(Empresa empresa) {
		return  empresa.getPromedioVentasAnuales() <= empresa.getActividad().getTopeMicro();
	}
	
}
