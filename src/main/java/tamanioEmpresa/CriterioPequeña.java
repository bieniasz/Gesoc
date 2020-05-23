package tamanioEmpresa;

public class CriterioPequeña extends CriterioTamanioEmpresa {


	@Override	
	public Boolean esCategoria(Empresa empresa) {
			         
	return  empresa.promedioVentasAnuales <= empresa.actividad.getPequenia() &&
			empresa.promedioVentasAnuales > empresa.actividad.getMicro()
			;
	}
	
	public void setMicro() {
		this.setTamanio("Pequenia");
	}
}
