package tamanioEmpresa.criterioTamanio;

import operacionComercial.EntidadPersistente;
import organizacion.categoria.Empresa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
