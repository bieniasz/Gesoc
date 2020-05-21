package usuario;

public abstract class Usuario {
    protected String usuario;
    protected String contrasenia;
    protected String tipoUsuario;

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
