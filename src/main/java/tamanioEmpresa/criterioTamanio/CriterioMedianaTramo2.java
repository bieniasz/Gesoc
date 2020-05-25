package tamanioEmpresa.criterioTamanio;

import organizacion.categoria.Empresa;

public class CriterioMedianaTramo2 extends CriterioTamanioEmpresa {
	public CriterioMedianaTramo2() {
		this.tamanio = "Mediana_tramo_2";
	}

	@Override
	public Boolean esCategoria(Empresa empresa) {
			         
	return  empresa.getPromedioVentasAnuales() <= empresa.getActividad().getTopeMedianaT2() &&
			empresa.getPromedioVentasAnuales() > empresa.getActividad().getTopeMedianaT1()
			;
	}
	
	public void setMensaje() {
		this.setTamanio("Mediana_tramo_2");
	}
}
