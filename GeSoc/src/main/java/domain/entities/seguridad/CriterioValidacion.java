package domain.entities.seguridad;

import domain.entities.seguridad.excepciones.LoginBloqueadoTemporalmenteException;
import domain.entities.seguridad.excepciones.UsuarioContraseniaInvalidosException;
import domain.entities.usuario.Usuario;

import java.util.List;

public interface CriterioValidacion {

	void validar(Usuario usuario, String contrasenia, List<String> mensajesDeError) throws LoginBloqueadoTemporalmenteException, UsuarioContraseniaInvalidosException;
}
