package organizacion.categoria;

import tamanioEmpresa.CalculadoraDeTamanio;
import tamanioEmpresa.Actividad;


public class Empresa {
	private final int cantidadDePersonal;
	private final Actividad actividad;
	private final double promedioVentasAnuales;
	private String tamanio;

	public Empresa (int cantPersonal, Actividad actividad, double promVentasAnuales) {
		this.cantidadDePersonal = cantPersonal;
		this.actividad = actividad;
		this.promedioVentasAnuales = promVentasAnuales;
	}

	public void calcularTamanio() {
		this.tamanio = CalculadoraDeTamanio.calcularTamanio(this);
	}


	//GETTERS
	public int getCantidadDePersonal() {
		return cantidadDePersonal;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public double getPromedioVentasAnuales() {
		return promedioVentasAnuales;
	}

	public String getTamanio() {
		return tamanio;
	}
}
