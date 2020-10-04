package tamanioEmpresa.criterioTamanio;

import organizacion.categoria.Empresa;
import tamanioEmpresa.Tamanios;

public class CriterioMicro extends CriterioTamanioEmpresa {
	public CriterioMicro() {
		this.tamanio = Tamanios.MICRO;
	}

	@Override	
	public Boolean esCategoria(Empresa empresa) {
		boolean esMicro;
		if(empresa.isComisionista())
			esMicro = empresa.getCantidadDePersonal() <= empresa.getActividad().getTopeCantPersonalMicro();
		else
			esMicro = empresa.getPromedioVentasAnuales() <= empresa.getActividad().getTopePromVentasMicro();

		return esMicro;
	}
}
