package domain.entities.tamanioEmpresa.criterioTamanio;

import domain.entities.organizacion.categoria.Empresa;
import domain.entities.tamanioEmpresa.Tamanios;

public class CriterioMedianaTramo2 extends CriterioTamanioEmpresa {
	public CriterioMedianaTramo2() {
		this.tamanio = Tamanios.MEDIANA_T2;
	}

	@Override
	public Boolean esCategoria(Empresa empresa) {
		boolean esMedianaT2;
		if(empresa.isComisionista()) {
			esMedianaT2 = empresa.getCantidadDePersonal() > empresa.getActividad().getTopeCantPersonalMedianaTramo1()
					&& empresa.getCantidadDePersonal() <= empresa.getActividad().getTopeCantPersonalMedianaTramo2();
		}
		else {
			esMedianaT2 = empresa.getPromedioVentasAnuales() > empresa.getActividad().getTopePromVentasMedianaT1()
					&& empresa.getPromedioVentasAnuales() <= empresa.getActividad().getTopePromVentasMedianaT2();
		}
		return esMedianaT2;
	}
}
