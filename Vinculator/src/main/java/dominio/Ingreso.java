package main.java.dominio;

import java.time.LocalDate;

public class Ingreso {
	String id_egreso;
	private LocalDate fecha; //
	Double valorTotal; // decimal
	String descripcion;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	} 
}
