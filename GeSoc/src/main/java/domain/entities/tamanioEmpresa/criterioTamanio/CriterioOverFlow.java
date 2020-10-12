package domain.entities.tamanioEmpresa.criterioTamanio;

import domain.entities.organizacion.categoria.Empresa;
import domain.entities.tamanioEmpresa.Tamanios;

public class CriterioOverFlow extends CriterioTamanioEmpresa {
	public CriterioOverFlow() {
		this.tamanio = Tamanios.OVERFLOW;
	}

	@Override
	public Boolean esCategoria(Empresa empresa) {
		boolean overflow;
		if(empresa.isComisionista())
			overflow = empresa.getCantidadDePersonal() > empresa.getActividad().getTopeCantPersonalMedianaTramo2();
		else
			overflow = empresa.getPromedioVentasAnuales() > empresa.getActividad().getTopePromVentasMedianaT2();

		return overflow;
	}
}
