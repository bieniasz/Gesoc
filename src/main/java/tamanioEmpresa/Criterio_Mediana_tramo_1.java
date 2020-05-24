package tamanioEmpresa;

import organizacion.categoria.Empresa;

public class Criterio_Mediana_tramo_1 extends CriterioTamanioEmpresa {

	@Override	
	public Boolean esCategoria(Empresa empresa) {
			         
	return  empresa.promedioVentasAnuales <= empresa.actividad.getmediana_tramo_1() &&
			empresa.promedioVentasAnuales > empresa.actividad.getPequenia()
			;
	}
	
	public void setMensaje() {
		this.setTamanio("Mediana_tramo_1");
	}
}
