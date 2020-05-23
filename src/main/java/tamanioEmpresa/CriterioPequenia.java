package tamanioEmpresa;

public class CriterioPequenia extends CriterioTamanioEmpresa {


	@Override	
	public Boolean esCategoria(Empresa empresa) {
			         
	return  empresa.promedioVentasAnuales <= empresa.actividad.getPequenia() &&
			empresa.promedioVentasAnuales > empresa.actividad.getMicro()
			;
	}
	
	public void setMensaje() {
		this.setTamanio("Pequenia");
	}
}
