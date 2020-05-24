package tamanioEmpresa;

import java.util.ArrayList;
import java.util.List;

import organizacion.categoria.Empresa;

//esta clase deberia ser un singleton?

public class CalculadoraDeTamanio {
	
	static List<CriterioTamanioEmpresa> criterios;

	 public static String calcularTamanio( Empresa empresa) {
		String respuesta;
		respuesta = criterios.stream().filter(criterio -> criterio.esCategoria(empresa)==true).findFirst().get().getTamanio();
		return respuesta;
	}
	 
	
	 public CalculadoraDeTamanio() {
	        this.criterios = new ArrayList<CriterioTamanioEmpresa>();
	        this.criterios.add(new CriterioMicro());
	        this.criterios.add(new CriterioPequenia());
	        this.criterios.add(new CriterioMedianaTramo1());
	        this.criterios.add(new CriterioMedianaTramo2());
	        this.criterios.add(new CriterioOverFlow());
	    }

}
