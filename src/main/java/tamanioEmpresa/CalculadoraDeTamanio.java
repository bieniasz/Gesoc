package tamanioEmpresa;

import java.util.List;

import organizacion.categoria.Empresa;

public class CalculadoraDeTamanio {
	// esta clase deberia ser un singleton
	static List<CriterioTamanioEmpresa> criterios;

	 public static String calcularTamanio( Empresa empresa) {
		String respuesta;
		respuesta = criterios.stream().filter(criterio -> criterio.esCategoria(empresa)==true).findFirst().get().getTamanio();
		return respuesta;
	}
}
