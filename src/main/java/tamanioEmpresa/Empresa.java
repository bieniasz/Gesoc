package tamanioEmpresa;

public class Empresa {

	int cantidadDePersonal;
	Actividad actividad;
	double promedioVentasAnuales;
	String tamanioCalculado;

	public void calcularTamanio() {
	
	this.tamanioCalculado=CalculadoraDeTamanio.calcularTamanio(this); 
		
	}
}
