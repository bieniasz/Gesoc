package tamanioEmpresa;

public class Criterio_OverFlow extends CriterioTamanioEmpresa {

	@Override	
	public Boolean esCategoria(Empresa empresa) {
			         
	return  empresa.promedioVentasAnuales > empresa.actividad.getmediana_tramo_2()
			;
	}
	
	public void setMensaje() {
		this.setTamanio("No_encaja_con_tamanios_actuales");
	}

}
