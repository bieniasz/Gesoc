package dominio; //

public class Egreso {

	String fecha ; //
	String valorTotal; // decimal
	String documentoComercial; // DocumentoComercial
	String detalle; // List<DetalleEgreso>
	String categoriasAsociadas; // List<CategoriaDeOperacion>
	String medioDePago; // MedioDePago
	String numeroIdentificadorMedioPago; // string
	String proveedor; // Proveedor
	String organizacion; // Organizacion
	String presupuestos; // List
	String ingresoAsociado; // OperacionIngreso
}
