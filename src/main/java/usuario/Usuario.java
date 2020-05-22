package usuario;

/**
 * TODO que una clase tan importante como usuario sea anemic.
 * Revisar esto. Ninguna clase a esta altura deberia ser anemica, esto solo seria
 * aceptado si es un DTO o una clase usada para contrato de interfaz.
 */

public abstract class Usuario {

    protected String usuario;
    protected String contrasenia;
    protected String tipoUsuario;

  /*  public Usuario(String usuario, String contrasenia) {

        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }*/

    public void iniciarSesion(String usuario, String contrasenia){

    }

    public void cambiarContrasenia(String contrasenia){

    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }



}
