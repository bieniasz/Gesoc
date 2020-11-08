package domain.entities.seguridad.excepciones;

public class UsuarioContraseniaInvalidosException extends Exception {
    public UsuarioContraseniaInvalidosException(){
        super("El usuario y/o la contraseña son inválidos");
    }
}
