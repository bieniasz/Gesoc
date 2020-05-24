package organizacion.categoria;

import tamanioEmpresa.CalculadoraDeTamanio;
import tamanioEmpresa.Actividad;


public class Empresa {

	int cantidadDePersonal;
	public Actividad actividad;
	public double promedioVentasAnuales;
	String tamanioCalculado;

	public void calcularTamanio() {
	
	this.tamanioCalculado=CalculadoraDeTamanio.calcularTamanio(this); 
		
	}
}
