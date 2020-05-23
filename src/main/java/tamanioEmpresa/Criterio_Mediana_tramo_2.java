package tamanioEmpresa;

public class Criterio_Mediana_tramo_2 extends CriterioTamanioEmpresa {

	@Override	
	public Boolean esCategoria(Empresa empresa) {
			         
	return  empresa.promedioVentasAnuales <= empresa.actividad.getmediana_tramo_2() &&
			empresa.promedioVentasAnuales > empresa.actividad.getmediana_tramo_1()
			;
	}
	
	public void setMensaje() {
		this.setTamanio("Mediana_tramo_2");
	}
}
