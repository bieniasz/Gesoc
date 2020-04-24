package seguridad;

import java.util.List;

public class Usuario {

	private String nombre;
	private String contrasenia;
	private String tipo;

	public String getNombre() {
		return nombre;
	}
	public String getContrasenia() {
		return contrasenia;
	}

	public String getTipo() {
		return tipo;
	}

	public Usuario (String _nombre, String _contrasenia){

		this.nombre = _nombre;
		this.contrasenia = _contrasenia;
	}
	

}
