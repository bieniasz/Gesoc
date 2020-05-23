package organizacion;

import operacionComercial.OperacionEgreso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Organizacion {
    protected List<OperacionEgreso> operacionesDeEgreso;

    public Map<String, String> getDatos(){
        return new HashMap<String, String>();
    }
}
