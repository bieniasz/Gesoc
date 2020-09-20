package organizacion.categoria;

import tamanioEmpresa.CalculadoraDeTamanio;
import tamanioEmpresa.Actividad;


public class Empresa extends Categoria{
	private int cantidadDePersonal;
	private Actividad actividad;
	private double promedioVentasAnuales;
	private String tamanio;
	private boolean comisionista;

	public Empresa (int cantPersonal, Actividad actividad, double promVentasAnuales, boolean esComisionista) {
		this.cantidadDePersonal = cantPersonal;
		this.actividad = actividad;
		this.promedioVentasAnuales = promVentasAnuales;
		this.comisionista = esComisionista;
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

	public boolean isComisionista() {
		return comisionista;
	}


	//SETTERS
    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public void setCantidadDePersonal(int cantidadDePersonal) {
        this.cantidadDePersonal = cantidadDePersonal;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public void setPromedioVentasAnuales(double promedioVentasAnuales) {
        this.promedioVentasAnuales = promedioVentasAnuales;
    }

	public void setComisionista(boolean comisionista) {
		this.comisionista = comisionista;
	}
}
