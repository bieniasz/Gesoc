package tamanioEmpresa;

import organizacion.categoria.Empresa;

public class CriterioMicro extends CriterioTamanioEmpresa {

	

	@Override	
	public Boolean esCategoria(Empresa empresa) {
			         
	return  empresa.promedioVentasAnuales <= empresa.actividad.getMicro();
	}
	
	public void setMensaje() {
		this.setTamanio("Micro");
	}
	
}

/// esto es un cambio