package organizaciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Organizacion {
    private List<String> operacionesDeEgreso; //TODO cambiar String por OperacionEgreso cuando este disponible

    public Map<String, String> getDatos(){
        return new HashMap<>();
    }
}
