package seguridad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlmacenContrasenias {
    //TODO Clase ValidadorDeContrasenia, cuando veamos db vamos a remodelar la clase para que no tenga los
    // datos en memoria y valla a buscar los datos que necesita en la base

    // TODO justificar porque usamos un Map de listas, y tener en cuenta los posibles problemas a futuro
    //  de esta decision.
    private Map<String, List<String>> contraseniasPrevias;
    private Integer periodosDeRotacion;
    private static AlmacenContrasenias instancia;
    private Map<String, IntentosFallidos> intentosFallidos;

    private AlmacenContrasenias() {
        this.contraseniasPrevias = new HashMap<String, List<String>>();
        this.intentosFallidos = new HashMap<String, IntentosFallidos>();
    }

    public static AlmacenContrasenias Instancia() {
        if (instancia == null) {
            instancia = new AlmacenContrasenias();
        }
        return instancia;
    }

    public void eliminarContraseniasAlmacenadas() {
        this.contraseniasPrevias.clear();
    }

    public synchronized void eliminarIntentosFallidosAlmacenados(){
        this.intentosFallidos.clear();
    }

    public synchronized void registrarContrasenia(String usuario, String contrasenia) {
        final List<String> contrasenias = this.contraseniasPrevias.get(usuario);
        try {
            contrasenias.add(contrasenia);
            if (contrasenias.size() > this.periodosDeRotacion) {
                contrasenias.remove(0);
            }

        } catch (NullPointerException e) {
            this.contraseniasPrevias.put(usuario, new ArrayList<String>());
            this.contraseniasPrevias.get(usuario).add(contrasenia);
        }

    }

    //TODO ojo con estos metodos que son de un singleton, deben ser synchronized para evitar problemas de manejo de threads
    //TODO java no garantiza safe-thread.
    public synchronized boolean contraseniaRepiteContraseniasViejas(String usuario, String contrasenia) {
        final List<String> contrasenias = this.contraseniasPrevias.get(usuario);

        try {
            return contrasenias.contains(contrasenia);
        } catch (Exception e) {
            return false;
        }
    }

    public synchronized void setPeriodosDeRotacion(Integer cantPeriodos){
        this.periodosDeRotacion = cantPeriodos;
    }

    public synchronized List<String> getContraseniasPreviasDeUsuario(String usuario) {
        return contraseniasPrevias.get(usuario);
    }

    public synchronized boolean compararContrasenia(String usuario,String contrasenia) {
        try {
            List<String> contraseniasPrevias = getContraseniasPreviasDeUsuario(usuario);
            if (contraseniasPrevias.get(contraseniasPrevias.size()-1) == contrasenia) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public synchronized boolean existeUsuario(String usuario){
        try {
            List<String> contraseniasPreviasDeUsuario = getContraseniasPreviasDeUsuario(usuario);
            if (contraseniasPreviasDeUsuario.size() > 0){

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public synchronized void agregarIntentoFallido(String usuario) {
        final IntentosFallidos intentosFallidos = this.intentosFallidos.get(usuario);

        try {
            intentosFallidos.nuevoIntentoFallido();
        } catch (NullPointerException n) {
            this.intentosFallidos.put(usuario, new IntentosFallidos());
            this.intentosFallidos.get(usuario).nuevoIntentoFallido();
        }
    }


        public synchronized IntentosFallidos getIntentosFallidosDeUsuario(String usuario){
        return this.intentosFallidos.get(usuario);
    }

    public synchronized void reiniciarIntentosFallidos(String usuario){
        this.intentosFallidos.get(usuario).reiniciarIntentos();
    }

    public synchronized void setHoraDelIntentoMaximo(String usuario){
        this.intentosFallidos.get(usuario).setHoraDelIntentoMaximo();
    }

    public synchronized void crearIntentoFallidoSiAplica(String usuario){
        if (this.existeUsuario(usuario) && !this.intentosFallidos.containsKey(usuario)){
            this.intentosFallidos.put(usuario, new IntentosFallidos());
        }
    }
}
