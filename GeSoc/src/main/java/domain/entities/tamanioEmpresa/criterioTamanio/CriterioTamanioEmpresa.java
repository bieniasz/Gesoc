package domain.entities.tamanioEmpresa.criterioTamanio;

import domain.entities.operacionComercial.EntidadPersistente;
import domain.entities.organizacion.categoria.Empresa;

public abstract class CriterioTamanioEmpresa extends EntidadPersistente{


	protected String tamanio;
	
	public String getTamanio() {
		return tamanio;
	}
	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	public abstract Boolean esCategoria(Empresa empresa);
}
