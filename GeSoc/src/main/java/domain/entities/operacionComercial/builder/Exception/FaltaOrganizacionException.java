package domain.entities.operacionComercial.builder.Exception;

public class FaltaOrganizacionException extends Exception{

    public FaltaOrganizacionException(){
        super("Falta seleccionar Organizacion");
    }
}
