package usuario;

import seguridad.ValidadorDeUsuario;

import java.util.List;

/**
 * TODO que una clase tan importante como usuario sea anemic.
 * Revisar esto. Ninguna clase a esta altura deberia ser anemica, esto solo seria
 * aceptado si es un DTO o una clase usada para contrato de interfaz.
 */

public class Usuario {

    protected String usuario;
    protected String contrasenia;
    protected Rol rol;

    public Usuario(String usuario, String contrasenia) throws Exception {
        ValidadorDeUsuario validador = new ValidadorDeUsuario();
        List<String> mensajesDeError = validador.validarCreacionContrasenia(usuario,contrasenia);

        try {
            if (mensajesDeError.size() > 0){
                throw new Exception();
            } else {
                this.usuario = usuario;
                this.contrasenia = contrasenia;
            }
        } catch (Exception e) {
           System.out.println("El usuario " + "\""+usuario+"\"" + " no pudo ser creado porque se presentaron los siguientes errores:");
           System.out.println(mensajesDeError);
           throw e;
        }
    }

    public void iniciarSesion(String usuario, String contrasenia){

    }

    public void cambiarContrasenia(String contrasenia) throws Exception {
        ValidadorDeUsuario validadorUsuario = new ValidadorDeUsuario();
        List<String> mensajesDeError = validadorUsuario.validarCreacionContrasenia(this.getUsuario(),contrasenia);

        try {
            if (mensajesDeError.size() > 0){
                throw new Exception();
            } else {
                this.contrasenia = contrasenia;
                System.out.println("La contraseña fue cambiada con exito");
            }
        } catch (Exception e) {
            System.out.println("La contraseña no pudo ser cambiada porque se presentaron los siguientes errores:");
            System.out.println(mensajesDeError);
            throw e;
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
