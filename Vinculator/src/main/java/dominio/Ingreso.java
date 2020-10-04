package main.java.dominio;

import java.time.LocalDate;

public class Ingreso {
	String id_ingreso;
	private LocalDate fecha; //
	private LocalDate fecha_hasta; //
	Double valorTotal; // decimal
	String descripcion;
	public String getId_Ingreso() {
		return id_ingreso;
	}
	public void setId_Ingreso(String id_egreso) {
		this.id_ingreso = id_egreso;
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
	public LocalDate getFecha_hasta() {
		return fecha_hasta;
	}
	public void setFecha_hasta(LocalDate fecha_hasta) {
		this.fecha_hasta = fecha_hasta;
	} 
}
