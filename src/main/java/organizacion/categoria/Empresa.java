package organizacion.categoria;

import tamanioEmpresa.CalculadoraDeTamanio;
import tamanioEmpresa.Actividad;

public class Empresa implements Categoria {
    private int cantidadDePersonal;
    private Actividad actividad;
    private float promedioVentasAnuales;
    private String tamanioCalculado;

    public String calcularTamanio(){
        return (new CalculadoraDeTamanio().calcularTamanio(this));
    }
}
