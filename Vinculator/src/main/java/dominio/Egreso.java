package main.java.dominio; //

import java.time.LocalDate;

public class Egreso {
	String id_egreso;
	private LocalDate fecha; //
	Double valorTotal; // decimal
	String documentoComercial; // DocumentoComercial
	String detalle; // List<DetalleEgreso>
	String categoriasAsociadas; // List<CategoriaDeOperacion>
	String medioDePago; // MedioDePago
	String numeroIdentificadorMedioPago; // string
	String proveedor; // Proveedor
	String organizacion; // Organizacion
	String presupuestos; // List
	String ingresoAsociado; // OperacionIngreso
	Boolean asignado;
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
	public String getCategoriasAsociadas() {
		return categoriasAsociadas;
	}
	public void setCategoriasAsociadas(String categoriasAsociadas) {
		this.categoriasAsociadas = categoriasAsociadas;
	}
	public String getMedioDePago() {
		return medioDePago;
	}
	public void setMedioDePago(String medioDePago) {
		this.medioDePago = medioDePago;
	}
	public String getNumeroIdentificadorMedioPago() {
		return numeroIdentificadorMedioPago;
	}
	public void setNumeroIdentificadorMedioPago(String numeroIdentificadorMedioPago) {
		this.numeroIdentificadorMedioPago = numeroIdentificadorMedioPago;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public String getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
	}
	public String getPresupuestos() {
		return presupuestos;
	}
	public void setPresupuestos(String presupuestos) {
		this.presupuestos = presupuestos;
	}
	public String getIngresoAsociado() {
		return ingresoAsociado;
	}
	public void setIngresoAsociado(String ingresoAsociado) {
		this.ingresoAsociado = ingresoAsociado;
	}
	public Boolean getAsignado() {
		return asignado;
	}
	public void setAsignado(Boolean asignado) {
		this.asignado = asignado;
	}
}
