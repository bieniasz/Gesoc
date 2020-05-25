package tamanioEmpresa.criterioTamanio;

import organizacion.categoria.Empresa;

public abstract class CriterioTamanioEmpresa {
	protected String tamanio;
	
	public String getTamanio() {
		return tamanio;
	}
	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	public abstract Boolean esCategoria(Empresa empresa);
}
