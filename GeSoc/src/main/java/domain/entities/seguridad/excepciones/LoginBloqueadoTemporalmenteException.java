package domain.entities.seguridad.excepciones;

public class LoginBloqueadoTemporalmenteException extends Exception {
    public LoginBloqueadoTemporalmenteException(){
        super("El login se bloqueó temporalmente. Intente de nuevo más tarde.");
    }

    public LoginBloqueadoTemporalmenteException(Integer tiempoDeEspera){
        super("Debe esperar " + tiempoDeEspera + " segundos para volver a loguearse.");
    }
}
