package main.java.dominio; //

import java.time.LocalDate;

public class Egreso {
	String id_egreso;
	LocalDate fecha; //
	Double valorTotal; // decimal
	String documentoComercial; // DocumentoComercial
	String detalle; // 
	Boolean asignado = false;

	public String getId_egreso() {
		return id_egreso;
	}
	public void setId_egreso(String id_egreso) {
		this.id_egreso = id_egreso;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getDocumentoComercial() {
		return documentoComercial;
	}
	public void setDocumentoComercial(String documentoComercial) {
		this.documentoComercial = documentoComercial;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	
	public Boolean isAsignado() {
		return asignado;
	}
	public void setAsignado(Boolean asignado) {
		this.asignado = asignado;
	}
}
