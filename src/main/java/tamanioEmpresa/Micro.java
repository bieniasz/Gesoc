package tamanioEmpresa;

public class Micro extends CriterioTamanioEmpresa {

	

	@Override	
	public Boolean esCategoria(Empresa empresa) {
			         
	return  empresa.promedioVentasAnuales <= empresa.actividad.getMicro();
	}
	
	public void setMicro() {
		this.setTamanio("Micro");
	}
	
}

