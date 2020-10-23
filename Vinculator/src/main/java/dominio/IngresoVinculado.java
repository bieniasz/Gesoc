package main.java.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IngresoVinculado {
	
	String id_ingreso;
	String Descripcion; // descripcion del ingreso
	List <String> egresos= new ArrayList<> ();// lista de id de egresos
	
	public IngresoVinculado(String id_ingreso,String Descripcion )  {
		this.id_ingreso=id_ingreso;
		this.Descripcion=Descripcion;
		
		
	}
	
	
	public String getId_ingreso() {
		return id_ingreso;
	}
	public void setId_ingreso(String id_ingreso) {
		this.id_ingreso = id_ingreso;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public List<String> getEgresos() {
		return egresos;
	}
	public void setEgresos(List<String> egresos) {
		this.egresos = egresos;
	}
	
	public void agregarEgreso(String egreso) {
		this.egresos.add(egreso);
	}
	
}