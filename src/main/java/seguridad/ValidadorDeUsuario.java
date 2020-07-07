package seguridad;

import usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ValidadorDeUsuario implements iValidadorDeUsuario{

    private final List<CriterioValidacion> criteriosCreacionContrasenia;

    public ValidadorDeUsuario() {

        this.criteriosCreacionContrasenia = new ArrayList<CriterioValidacion>();
        this.criteriosCreacionContrasenia.add(new CriterioCaracteresEspeciales());
        this.criteriosCreacionContrasenia.add(new CriterioFueraListaNegra());
        this.criteriosCreacionContrasenia.add(new CriterioLongitud());
        this.criteriosCreacionContrasenia.add(new CriterioMinusculasYMayusculas());
        this.criteriosCreacionContrasenia.add(new CriterioRotacionContrasenia());
    }

    public List<String> validarCreacionContrasenia(String usuario, String contrasenia) {

        final List<String> errores = new ArrayList<String>();
        this.criteriosCreacionContrasenia.forEach(criterio -> criterio.validar(usuario,contrasenia,errores));

        if (errores.size() == 0) {
            AlmacenContrasenias.Instancia().registrarContrasenia(usuario, contrasenia);
        }

        return errores;
    }

    /**
     * 1- los metodos en java comienzan con minuscula.
     * 2- si un metodo no se utiliza en el codigo no debe existir.
     * @param usuario
     * @return
     */

    public Usuario crearUsuario(String usuario, String contrasenia) throws Exception{
        List<String> mensajesDeError = this.validarCreacionContrasenia(usuario,contrasenia);

        try {
            if (mensajesDeError.size() > 0){
                throw new Exception();
            } else {
                return new Usuario(usuario);
            }
        } catch (Exception e) {
            //TODO cambiar para que no haga print por pantalla.
            System.out.println("El usuario " + "\"" + usuario + "\"" + " no pudo ser creado porque se presentaron los siguientes errores:");
            System.out.println(mensajesDeError);
            throw e;
        }
    }

    public List<String> validarContraseniaLogin(String usuario, String contrasenia) {

        List<String> mensajesDeError = new ArrayList<>();
        CriterioLogin criterioLogin = new CriterioLogin();
        CriterioTiempoLogin criterioTiempoLogin = new CriterioTiempoLogin();

        criterioLogin.validar(usuario, contrasenia, mensajesDeError);

        if(mensajesDeError.size() == 1){
            criterioTiempoLogin.errorAlLogear(usuario);
        } else{
            criterioTiempoLogin.validar(usuario,contrasenia,mensajesDeError);
        }

        return mensajesDeError;
    }
}
