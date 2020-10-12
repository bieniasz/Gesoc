package domain.entities.tamanioEmpresa.criterioTamanio;

import domain.entities.organizacion.categoria.Empresa;
import domain.entities.tamanioEmpresa.Tamanios;

public class CriterioMedianaTramo1 extends CriterioTamanioEmpresa {
	public CriterioMedianaTramo1() {
		this.tamanio = Tamanios.MEDIANA_T1;
	}

	@Override
	public Boolean esCategoria(Empresa empresa) {
		boolean esMedianaT1;
		if(empresa.isComisionista()) {
			esMedianaT1 = empresa.getCantidadDePersonal() > empresa.getActividad().getTopeCantPersonalPequenia()
					&& empresa.getCantidadDePersonal() <= empresa.getActividad().getTopeCantPersonalMedianaTramo1();
		}
		else {
			esMedianaT1 = empresa.getPromedioVentasAnuales() > empresa.getActividad().getTopePromVentasPequenia()
					&& empresa.getPromedioVentasAnuales() <= empresa.getActividad().getTopePromVentasMedianaT1();
		}
		return esMedianaT1;
	}
}
