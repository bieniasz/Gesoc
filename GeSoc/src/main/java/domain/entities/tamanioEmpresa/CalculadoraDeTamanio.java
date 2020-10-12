package domain.entities.tamanioEmpresa;

import java.util.ArrayList;
import java.util.List;

import domain.entities.organizacion.categoria.Empresa;
import domain.entities.tamanioEmpresa.criterioTamanio.*;

//esta clase deberia ser un singleton?

public class CalculadoraDeTamanio {
	
	static List<CriterioTamanioEmpresa> criterios;

	static {
        criterios = new ArrayList<CriterioTamanioEmpresa>();
        criterios.add(new CriterioMicro());
        criterios.add(new CriterioPequenia());
        criterios.add(new CriterioMedianaTramo1());
        criterios.add(new CriterioMedianaTramo2());
        criterios.add(new CriterioOverFlow());
    }

    public static String calcularTamanio(Empresa empresa) {
        String respuesta;
        respuesta = criterios.stream().filter(criterio -> criterio.esCategoria(empresa)).findFirst().get().getTamanio();
        return respuesta;
    }
}
