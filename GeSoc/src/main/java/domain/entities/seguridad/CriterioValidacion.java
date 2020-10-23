package domain.entities.seguridad;

import domain.entities.usuario.Usuario;

import java.util.List;

public interface CriterioValidacion {

	public void validar(Usuario usuario, String contrasenia, List<String> mensajesDeError);
}