package tamanioEmpresa;

import organizacion.categoria.Empresa;

public class CriterioOverFlow extends CriterioTamanioEmpresa {

	@Override	
	public Boolean esCategoria(Empresa empresa) {
			         
	return  empresa.promedioVentasAnuales > empresa.actividad.getmediana_tramo_2()
			;
	}
	
	public void setMensaje() {
		this.setTamanio("No_encaja_con_tamanios_actuales");
	}

}
