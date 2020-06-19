package tamanioEmpresa.criterioTamanio;

import organizacion.categoria.Empresa;
import tamanioEmpresa.Tamanios;

public class CriterioPequenia extends CriterioTamanioEmpresa {

	public CriterioPequenia() {
		this.tamanio = Tamanios.PEQUENIA;
	}

	@Override	
	public Boolean esCategoria(Empresa empresa) {
		boolean esPequena;
		if(empresa.isComisionista()) {
			esPequena = empresa.getCantidadDePersonal() > empresa.getActividad().getTopeCantPersonalMicro()
					&& empresa.getCantidadDePersonal() <= empresa.getActividad().getTopeCantPersonalPequenia();
		}
		else {
			esPequena = empresa.getPromedioVentasAnuales() > empresa.getActividad().getTopePromVentasMicro()
					&& empresa.getPromedioVentasAnuales() <= empresa.getActividad().getTopePromVentasPequenia();
		}
		return esPequena;
	}
}
