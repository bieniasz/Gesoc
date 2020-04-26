package seguridad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlmacenContrasenias {

    private Map<String, List<String>> contraseniasPrevias;
    private Integer periodosDeRotacion;
    private static AlmacenContrasenias instancia;

    private AlmacenContrasenias() {
        this.contraseniasPrevias = new HashMap<String,List<String>>();
        this.periodosDeRotacion = 3;
    }

    public static AlmacenContrasenias Instancia(){
        if (instancia == null) {
            instancia = new AlmacenContrasenias();
        }

        return instancia;
    }

    public void eliminarContraseniasAlmacenadas(){
        this.contraseniasPrevias.clear();
    }


    public void registrarContrasenia(Usuario usuario){

        List<String> contrasenias = this.contraseniasPrevias.get(usuario.getNombre());

        try {
            contrasenias.add(usuario.getContrasenia());

            if (contrasenias.size() > this.periodosDeRotacion){
                contrasenias.remove(0);
            }

        } catch (Exception e) {
            this.contraseniasPrevias.put(usuario.getNombre(), new ArrayList<String>());
            this.contraseniasPrevias.get(usuario.getNombre()).add(usuario.getContrasenia());
        }

    }

    public boolean contraseniaRepiteContraseniasViejas(Usuario usuario){

        List<String> contrasenias = this.contraseniasPrevias.get(usuario.getNombre());

        try{
            return contrasenias.contains(usuario.getContrasenia());
        } catch (Exception e){
            return false;
        }
    }
}
