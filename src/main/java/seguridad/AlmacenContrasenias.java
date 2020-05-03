package seguridad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlmacenContrasenias {

    // TODO justificar porque usamos un Map de listas, y tener en cuenta los posibles problemas a futuro de esta decision.
    private Map<String, List<String>> contraseniasPrevias;
    // TODO si esto es una variable static definirla como final static
    private Integer periodosDeRotacion;
    private static AlmacenContrasenias instancia;

    private AlmacenContrasenias() {
        this.contraseniasPrevias = new HashMap<String, List<String>>();
        // TODO esto es un code smell magic number
        this.periodosDeRotacion = 3;
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
    public void registrarContrasenia(Usuario usuario) {
        // TODO siempre se deben utilizar en el uso de variables de scope de metodo un final.
        List<String> contrasenias = this.contraseniasPrevias.get(usuario.getNombre());
        // TODO si el usuario no tiene ningun password la linea 40 parece dar nullPointerException
        // TODO una mejor forma es trabajar con Optional.
        try {
            contrasenias.add(usuario.getContrasenia());

            if (contrasenias.size() > this.periodosDeRotacion) {
                contrasenias.remove(0);
            }

        } catch (Exception e) {
            this.contraseniasPrevias.put(usuario.getNombre(), new ArrayList<String>());
            this.contraseniasPrevias.get(usuario.getNombre()).add(usuario.getContrasenia());
        }

    }

    //TODO ojo con estos metodos que son de un singleton, deben ser synchronized para evitar problemas de manejo de threads
    //TODO java no garantiza safe-thread.
    public boolean contraseniaRepiteContraseniasViejas(Usuario usuario) {
        // TODO siempre se deben utilizar en el uso de variables de scope de metodo un final.
        List<String> contrasenias = this.contraseniasPrevias.get(usuario.getNombre());

        try {
            return contrasenias.contains(usuario.getContrasenia());
        } catch (Exception e) {
            return false;
        }
    }
}
