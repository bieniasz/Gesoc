package seguridad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlmacenContrasenias {
    //TODO Clase ValidadorDeContrasenia, cuando veamos db vamos a remodelar la clase para que no tenga los datos en memoria y valla a buscar los datos que necesita en la base

    // TODO justificar porque usamos un Map de listas, y tener en cuenta los posibles problemas a futuro de esta decision.
    private Map<String, List<String>> contraseniasPrevias;
    private Integer periodosDeRotacion;
    private static AlmacenContrasenias instancia;

    private AlmacenContrasenias() {
        this.contraseniasPrevias = new HashMap<String, List<String>>();
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


    //TODO ojo con estos metodos que son de un singleton, deben ser synchronized para evitar problemas de manejo de threads
    //TODO java no garantiza safe-thread.
    public synchronized void registrarContrasenia(Usuario usuario) {
        final List<String> contrasenias = this.contraseniasPrevias.get(usuario.getNombre());
        // TODO si el usuario no tiene ningun password la linea 40 parece dar NullPointerException
        // TODO una mejor forma es trabajar con Optional.
        try {
            contrasenias.add(usuario.getContrasenia());

            if (contrasenias.size() > this.periodosDeRotacion) {
                contrasenias.remove(0);
            }

        } catch (NullPointerException e) {
            this.contraseniasPrevias.put(usuario.getNombre(), new ArrayList<String>());
            this.contraseniasPrevias.get(usuario.getNombre()).add(usuario.getContrasenia());
        }

    }

    //TODO ojo con estos metodos que son de un singleton, deben ser synchronized para evitar problemas de manejo de threads
    //TODO java no garantiza safe-thread.
    public synchronized boolean contraseniaRepiteContraseniasViejas(Usuario usuario) {
        final List<String> contrasenias = this.contraseniasPrevias.get(usuario.getNombre());

        try {
            return contrasenias.contains(usuario.getContrasenia());
        } catch (Exception e) {
            return false;
        }
    }

    public void setPeriodosDeRotacion(Integer cantPeriodos){
        this.periodosDeRotacion = cantPeriodos;
    }
}
