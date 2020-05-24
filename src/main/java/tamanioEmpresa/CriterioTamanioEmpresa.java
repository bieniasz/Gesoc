package tamanioEmpresa;

import organizacion.categoria.Empresa;

public abstract class CriterioTamanioEmpresa {

	private String tamanio;
	
	 public Boolean esCategoria(Empresa empresa) {
	
	return false;
	 }

	public String getTamanio() {
		return tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}
}
